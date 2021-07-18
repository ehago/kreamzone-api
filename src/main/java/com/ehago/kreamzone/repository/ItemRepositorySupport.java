package com.ehago.kreamzone.repository;

import com.ehago.kreamzone.dto.response.brand.BrandResponseDto;
import com.ehago.kreamzone.dto.response.item.ItemResponseDto;
import com.ehago.kreamzone.entity.Item;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class ItemRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public ItemRepositorySupport(JPAQueryFactory queryFactory) {
        super(Item.class);
        this.queryFactory = queryFactory;
    }

    public List<ItemResponseDto> selectDroppedItems() {
        Query nativeQuery = Objects.requireNonNull(this.getEntityManager()).createNativeQuery(
                "SELECT br.name, br.image as brand_image, br.background, i.eng_name, i.image as item_image, b.immediately_purchase_price " +
                        "FROM kream.item i " +
                        "INNER JOIN " +
                        "kream.brand br " +
                        "on br.brand_id = i.brand_id " +
                        "LEFT JOIN " +
                        "( " +
                        "SELECT item_id, MIN(price) AS immediately_purchase_price " +
                        "FROM kream.bid " +
                        "GROUP BY item_id " +
                        ") b " +
                        "ON i.item_id = b.item_id " +
                        "ORDER BY release_date DESC " +
                        "LIMIT 16"
        );

        List<Object[]> resultList = nativeQuery.getResultList();

        return resultList
                .stream()
                .map(obj -> new ItemResponseDto(
                        new BrandResponseDto(
                                (String) obj[0],
                                (String) obj[1],
                                (String) obj[2]
                        ),
                        (String) obj[3],
                        (String) obj[4],
                        obj[5] != null ? Integer.parseInt((String) obj[5]) : 0)
                )
                .collect(Collectors.toList());
    }

}
