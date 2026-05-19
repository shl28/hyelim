package com.example.roomfit.recommend;

import com.example.roomfit.domain.InteriorPost;
import com.example.roomfit.domain.InteriorStyle;
import com.example.roomfit.domain.Member;
import com.example.roomfit.domain.PostStatus;
import com.example.roomfit.domain.UserProfile;
import com.example.roomfit.dto.RecommendResultDto;
import com.example.roomfit.dto.ScoredPostDto;
import com.example.roomfit.repository.InteriorPostRepository;
import com.example.roomfit.repository.MemberRepository;
import com.example.roomfit.repository.PostLikeRepository;
import com.example.roomfit.repository.UserProfileRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RecommendEngine {

    private final UserProfileRepository userProfileRepository;
    private final InteriorPostRepository interiorPostRepository;
    private final PostLikeRepository postLikeRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public RecommendResultDto recommend(Long memberId) {
        UserProfile profile = userProfileRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalStateException("추천 프로필을 먼저 등록해 주세요."));

        List<InteriorPost> candidates = interiorPostRepository
                .findTop50ByStatusOrderByCreatedAtDesc(PostStatus.VISIBLE);
        // 후보글 : 최신 VISIBLE 글 최대 50건

        List<ScoredPostDto> profileBased = scoreByProfile(profile, candidates, 10); // 프로필 점수: 상위 10개
        List<InteriorPost> popular = interiorPostRepository
                .findTop20ByStatusOrderByLikeCountDescViewCountDescCreatedAtDesc(
                        PostStatus.VISIBLE, PageRequest.of(0, 5));
        List<ScoredPostDto> cfBased = recommendBySimilarUsers(memberId, profile, 5);

        List<ScoredPostDto> merged = mergeResults(profileBased, cfBased, popular);
        List<String> colors = ColorPaletteTable.get(profile.getPreferredStyle());
        String layout = buildLayoutAdvice(profile);

        return RecommendResultDto.builder()
                .posts(merged)
                .colorPalette(colors)
                .layoutAdvice(layout)
                .preferredStyle(profile.getPreferredStyle())
                .build();
    }

    // 후보 50건 각각에 calculateProfileScore 적용
    private List<ScoredPostDto> scoreByProfile(UserProfile profile, List<InteriorPost> candidates, int limit) {
        return candidates.stream()
                .map(post -> new ScoredPostDto(post, calculateProfileScore(profile, post)))
                .filter(s -> s.score() > 0) // 점수 0점 초과만 남김
                .sorted(Comparator.comparingInt(ScoredPostDto::score).reversed()) // 점수 내림차순 정렬
                .limit(limit) // 상위 (limit)10건 추출
                .toList();
    }

    // calculateProfileScore
    // 글 스타일 == 선호 스타일 : 점수 40점 부여
    // 평수차 <= 2: 25점 부여
    // 평수차 <= 5: 10점 부여
    // 예산차 <= 20% : 20점 부여
    // 예산차 <= 50% : 5점 부여
    // 가구소유여부 동일 : 10점 부여
    // log(likeCount + 1) : 최대 5점까지 부여가능
    // 내조건과 비슷한 인테리어 글을 숫자로 순위 매김

    int calculateProfileScore(UserProfile profile, InteriorPost post) {
        int score = 0;
        if (post.getStyle() == profile.getPreferredStyle()) {
            score += 40;
        }
        if (post.getRoomSize() != null) {
            double diff = post.getRoomSize().subtract(profile.getRoomSize()).abs().doubleValue();
            if (diff <= 2) {
                score += 25;
            } else if (diff <= 5) {
                score += 10;
            }
        }
        if (profile.getBudget() > 0 && post.getBudget() != null) {
            double ratio = Math.abs(post.getBudget() - profile.getBudget()) / (double) profile.getBudget();
            if (ratio <= 0.2) {
                score += 20;
            } else if (ratio <= 0.5) {
                score += 5;
            }
        }
        if (post.isHasFurnitureTag() == profile.isHasFurniture()) {
            score += 10;
        }
        score += Math.min(5, (int) Math.log(post.getLikeCount() + 1));
        return score;
    }

    // 비슷한 회원 찾기

    private List<ScoredPostDto> recommendBySimilarUsers(Long memberId, UserProfile profile, int limit) {
        // 1. memberRepository.findAll() 필터해서 나를 제외한 모든 회원 추출
        List<Member> others = memberRepository.findAll().stream()
                .filter(m -> !m.getId().equals(memberId))
                .toList();

        // 2. 다른 유저의 userProfile과 코사인유사도계산
        // 3. 유사도 > 0.3 만, 상위 5명 = 이웃(neighbors)
        List<SimilarMember> neighbors = others.stream()
                .map(other -> {
                    UserProfile otherProfile = userProfileRepository.findByMemberId(other.getId()).orElse(null);
                    double sim = otherProfile == null ? 0 : cosineSimilarity(profile, otherProfile);
                    return new SimilarMember(other.getId(), sim);
                })
                .filter(s -> s.similarity > 0.3)
                .sorted(Comparator.comparingDouble(SimilarMember::similarity).reversed())
                .limit(5)
                .toList();

        if (neighbors.isEmpty()) {
            return List.of();
        }

        List<Long> neighborIds = neighbors.stream().map(SimilarMember::memberId).toList();
        // findPostIdsLikedByMembers(neighborIds) : 이웃들이 좋아요 누른 글 ID 목록
        List<Long> likedPostIds = postLikeRepository.findPostIdsLikedByMembers(neighborIds);
        // 내가 이미 좋아요 한 글은 제외
        Set<Long> myLikes = postLikeRepository.findByMemberId(memberId).stream()
                .map(pl -> pl.getPost().getId())
                .collect(Collectors.toSet());

        // cfScore : 각 글 id 마다 이웃이 그글에 좋아요 했으면 이웃.similarity * 2 합산
        // -> 점수가 높은 순 5건, InteriorPost 로드 후 ScoredPostDto
        // 의미 : 나와 프로필이 비슷한 사람이 좋아한 글
        Map<Long, Double> cfScores = likedPostIds.stream()
                .filter(id -> !myLikes.contains(id))
                .collect(Collectors.groupingBy(id -> id, Collectors.summingDouble(id -> {
                    double sum = 0;
                    for (SimilarMember n : neighbors) {
                        boolean liked = postLikeRepository.existsByMemberIdAndPostId(n.memberId(), id);
                        if (liked) {
                            sum += n.similarity() * 2;
                        }
                    }
                    return sum;
                })));


        return cfScores.entrySet().stream()
                .sorted(Map.Entry.<Long, Double>comparingByValue().reversed())
                .limit(limit)
                .map(e -> interiorPostRepository.findById(e.getKey())
                        .map(p -> new ScoredPostDto(p, e.getValue().intValue()))
                        .orElse(null))
                .filter(java.util.Objects::nonNull)
                .toList();
    }

    // cosineSimilarity + toVector
    // 평수 roomSize / 30
    // 예산 budget / 1000
    // 스타일 enum.ordinal / 스타일 개수
    // 가구 있으면 1 없으면 0
    // 비교해서 벡터 방향이 비슷할수록 유사도 높아짐(스케일 다른 값도 어느정도 비교가능)
    private double cosineSimilarity(UserProfile a, UserProfile b) {
        double[] va = toVector(a);
        double[] vb = toVector(b);
        double dot = 0, na = 0, nb = 0;
        for (int i = 0; i < va.length; i++) {
            dot += va[i] * vb[i];
            na += va[i] * va[i];
            nb += vb[i] * vb[i];
        }
        if (na == 0 || nb == 0) {
            return 0;
        }
        return dot / (Math.sqrt(na) * Math.sqrt(nb));
    }

    private double[] toVector(UserProfile p) {
        double sizeNorm = p.getRoomSize().doubleValue() / 30.0;
        double budgetNorm = p.getBudget() / 1000.0;
        double styleNorm = p.getPreferredStyle().ordinal() / (double) InteriorStyle.values().length;
        double furniture = p.isHasFurniture() ? 1.0 : 0.0;
        return new double[] {sizeNorm, budgetNorm, styleNorm, furniture};
    }

    // 인기글 + mergeResults
    // recommend 안에서 repository
    // mergeResults 병합순서
    // LinkedHashSet<>(); 글 id 중복 제거

    private List<ScoredPostDto> mergeResults(
            List<ScoredPostDto> profileBased,
            List<ScoredPostDto> cfBased,
            List<InteriorPost> popular) {
        Set<Long> seen = new LinkedHashSet<>();
        List<ScoredPostDto> result = new ArrayList<>();
        for (ScoredPostDto dto : profileBased) {
            if (seen.add(dto.post().getId())) {
                result.add(dto);
            }
        }
        for (ScoredPostDto dto : cfBased) {
            if (seen.add(dto.post().getId())) {
                result.add(dto);
            }
        }
        for (InteriorPost post : popular) {
            if (seen.add(post.getId())) {
                result.add(new ScoredPostDto(post, popularityScore(post)));
            }
        }
        return result.stream().limit(15).toList();
    }

    // decay = 1/(1+경과일 / 7) 오래될 수록 감소
    public static int popularityScore(InteriorPost post) {
        double base = post.getViewCount() * 0.1 + post.getLikeCount() * 2 + post.getCommentCount() * 1.5;
        long days = ChronoUnit.DAYS.between(post.getCreatedAt(), LocalDateTime.now());
        double decay = 1.0 / (1.0 + days / 7.0);
        return (int) (base * decay);
    }

    private String buildLayoutAdvice(UserProfile profile) {
        List<String> lines = new ArrayList<>();
        if (profile.getRoomSize().compareTo(BigDecimal.valueOf(10)) < 0) {
            lines.add("침대 헤드를 창 반대편 벽에 두어 동선을 확보하세요.");
            lines.add("접이식 테이블·소파베드를 검토해 보세요.");
        }
        if (!profile.isHasFurniture()) {
            lines.add("침대·책상·수납 3종을 먼저 배치한 뒤 소품을 추가하세요.");
        }
        if ("재택".equals(profile.getLifestyle())) {
            lines.add("책상은 자연광 옆, 모니터는 창과 수직으로 배치하세요.");
        }
        if (lines.isEmpty()) {
            lines.add("가구 배치 후 통로 폭 80cm 이상을 유지하면 livable한 원룸이 됩니다.");
        }
        return String.join("\n", lines);
    }

    private record SimilarMember(Long memberId, double similarity) {}
}
