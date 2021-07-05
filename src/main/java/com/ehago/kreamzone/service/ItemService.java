package com.ehago.kreamzone.service;

import com.ehago.kreamzone.dto.response.item.ItemResponseDto;
import com.ehago.kreamzone.entity.Item;
import com.ehago.kreamzone.mapper.ItemMapper;
import com.ehago.kreamzone.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Transactional
    public List<ItemResponseDto> getDroppedItems() {
        List<Item> items = itemRepository.findAllJoinFetch();
        return itemMapper.toDtos(items);
    }

    @Transactional
    public List<ItemResponseDto> getPopularItems() {
        List<Item> items = itemRepository.findAllJoinFetch();
        return itemMapper.toDtos(items);
    }
}