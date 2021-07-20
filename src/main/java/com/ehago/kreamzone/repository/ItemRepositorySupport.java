package com.ehago.kreamzone.repository;

import com.ehago.kreamzone.dto.response.brand.BrandResponseDto;
import com.ehago.kreamzone.dto.response.item.ItemResponseDto;
import com.ehago.kreamzone.entity.Item;
import com.ehago.kreamzone.mapper.ItemMapper;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.ehago.kreamzone.entity.QBid.bid;
import static com.ehago.kreamzone.entity.QItem.item;

@Repository
public class ItemRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;
    private final ItemMapper itemMapper;

    public ItemRepositorySupport(JPAQueryFactory queryFactory, ItemMapper itemMapper) {
        super(Item.class);
        this.queryFactory = queryFactory;
        this.itemMapper = itemMapper;
    }

    public List<ItemResponseDto> selectDroppedItems() {
        Query nativeQuery = Objects.requireNonNull(this.getEntityManager()).createNativeQuery(
                "SELECT br.name, br.image as brand_image, br.background, i.item_id, i.eng_name, i.image as item_image, b.immediately_purchase_price " +
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
                        ((BigInteger) obj[3]).longValue(),
                        new BrandResponseDto(
                                (String) obj[0],
                                (String) obj[1],
                                (String) obj[2]
                        ),
                        (String) obj[4],
                        (String) obj[5],
                        obj[6] != null ? Integer.parseInt((String) obj[6]) : 0)
                )
                .collect(Collectors.toList());
    }

    public List<ItemResponseDto> selectPopularItems() {
        List<Tuple> subItems = this.queryFactory
                .select(bid.item.itemId, bid.price.min())
                .from(bid)
                .groupBy(bid.item)
                .orderBy(bid.count().desc())
                .limit(16)
                .fetch();

        List<Long> itemIds = subItems.stream().map(subItem -> subItem.get(bid.item.itemId)).collect(Collectors.toList());

        List<Item> items = this.queryFactory
                .select(item)
                .from(item)
                .innerJoin(item.brand)
                .fetchJoin()
                .where(item.itemId.in(itemIds))
                .fetch();

        List<ItemResponseDto> result = items.stream().map(itemMapper::toDto).collect(Collectors.toList());

        IntStream
                .range(0, items.size())
                .forEach(idx ->
                        result.get(idx).setImmediatelyPurchasePrice(
                                Integer.parseInt(
                                        Optional.of(subItems)
                                                .map(it -> it.get(idx))
                                                .map(it -> it.get(bid.price.min()))
                                                .orElse("0")))
                );

        return result;
    }

}
