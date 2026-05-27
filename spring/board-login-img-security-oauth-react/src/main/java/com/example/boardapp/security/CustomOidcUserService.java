package com.example.boardapp.security;

import com.example.boardapp.domain.Member;
import com.example.boardapp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOidcUserService extends OidcUserService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oidcUser.getSubject();

        String email = oidcUser.getEmail();
        String name = oidcUser.getFullName();
        if (name == null || name.isBlank()) {
            name = email != null ? email.split("@")[0] : "user";
        }

        log.info("OAuth 로그인: provider={}, providerId={}, email={}, name={}",
                provider, providerId, email, name);

        final String finalEmail = email;
        final String finalName = name;

        Member member = memberRepository
                .findByOauthProviderAndOauthProviderId(provider, providerId)
                .orElseGet(() -> createNewOAuthMember(provider, providerId, finalEmail, finalName));

        member.updateOAuthInfo(email, name);

        return new LoginOidcUser(member, oidcUser);
    }

    private Member createNewOAuthMember(String provider, String providerId, String email, String name) {

        String username = provider + "_" + providerId;

        Member member = Member.builder()
                .username(username)
                .password(null)
                .nickname(name)
                .email(email)
                .oauthProvider(provider)
                .oauthProviderId(providerId)
                .build();

        log.info("새 OAuth 회원 생성: username={}", username);
        return memberRepository.save(member);
    }
}
