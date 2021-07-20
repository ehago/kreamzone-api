package com.ehago.kreamzone.service;

import com.ehago.kreamzone.dto.response.item.ItemResponseDto;
import com.ehago.kreamzone.entity.Item;
import com.ehago.kreamzone.mapper.ItemMapper;
import com.ehago.kreamzone.repository.ItemRepositorySupport;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.ehago.kreamzone.entity.QBid.bid;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepositorySupport itemRepositorySupport;
    private final ItemMapper itemMapper;


    public List<ItemResponseDto> getDroppedItems() {
        return itemRepositorySupport.selectDroppedItems();
    }

    public List<ItemResponseDto> getPopularItems() {
        List<Tuple> itemIdAndMinPrice = itemRepositorySupport.findItemIdAndMinPrice();
        List<Long> itemIds = itemIdAndMinPrice.stream().map(item -> item.get(bid.item.itemId)).collect(Collectors.toList());
        List<Item> items = itemRepositorySupport.findPopularItems(itemIds);
        List<ItemResponseDto> result = items.stream().map(itemMapper::toDto).collect(Collectors.toList());
        IntStream
                .range(0, items.size())
                .forEach(idx ->
                        result.get(idx).setImmediatelyPurchasePrice(
                                Integer.parseInt(
                                        Optional.of(itemIdAndMinPrice)
                                                .map(it -> it.get(idx))
                                                .map(it -> it.get(bid.price.min()))
                                                .orElse("0")
                                )
                        )
                );
        return result;
    }

}