package com.shop.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemSearchDto;
import com.shop.entity.Item;
import com.shop.entity.QItem;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {
    // queryDSL 쿼리를 실행할 객체 생성
    private JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    // BooleanExpression : 동적 쿼리를 위한 메서드 searchSellStatusEq 판매상태가 (판매중/품절)
    private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus) {
        return searchSellStatus == null ? null : QItem.item.itemSellStatus.eq(searchSellStatus);
    }

    private BooleanExpression regDtsAfter(String searchDateType) {
        LocalDateTime dateTime = LocalDateTime.now();

        if (StringUtils.equals("all", searchDateType) || searchDateType == null) {
            return null;
        } else if (StringUtils.equals("1d", searchDateType)) {
            dateTime = dateTime.minusDays(1);
        } else if (StringUtils.equals("1w", searchDateType)) {
            dateTime = dateTime.minusWeeks(1);
        } else if (StringUtils.equals("1m", searchDateType)) {
            dateTime = dateTime.minusMonths(1);
        } else if (StringUtils.equals("6m", searchDateType)) {
            dateTime = dateTime.minusMonths(6);
        }

        return QItem.item.regTime.after(dateTime);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery) {
        if (StringUtils.equals("itemNm", searchBy)) {
            return QItem.item.itemNm.like("%" + searchQuery + "%");
        } else if (StringUtils.equals("createdBy", searchBy)) {
            return QItem.item.createdBy.like("%" + searchQuery + "%");
        }

        return null;
    }

    @Override
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        List<Item> content = queryFactory
                .selectFrom(QItem.item)  // item 테이블로 부터 모든 컬럼을 조회(select * from item)
                .where(regDtsAfter(itemSearchDto.getSearchDateType()), //등록 날짜 조회 조건
                        searchSellStatusEq(itemSearchDto.getSearchSellStatus()),  // 조회조건: 판매상태
                        searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()) // 조회조건: 검색어(상품명 또는 작성자)
                )
                .orderBy(QItem.item.id.desc()) // id 역순으로 정렬
                .offset(pageable.getOffset()) // 데이터를 가져올 시작 위치 (2페이지면 10번 부터)
                .limit(pageable.getPageSize()) // 한페이지에 보여줄 데이터 개수(예: 10개씩)
                .fetch(); // 쿼리를 실행하고 결과를 리스트로 반환함

        // 페이징 네비게이션 [이전][1][2]...[다음] 만들려면 total 값 있어야 가능
        long total = queryFactory.select(Wildcard.count).from(QItem.item)
                .where(regDtsAfter(itemSearchDto.getSearchDateType()),
                        searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                        searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
                .fetchOne(); // count 쿼리는 결과가 항상 숫자 하나로 반환되어서 fetchone씀
        // select count(*)

        return new PageImpl<>(content, pageable, total);
    }
}
