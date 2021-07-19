package com.ehago.kreamzone.service;

import com.ehago.kreamzone.dto.response.item.ItemResponseDto;
import com.ehago.kreamzone.repository.ItemRepositorySupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepositorySupport itemRepositorySupport;

    public List<ItemResponseDto> getDroppedItems() {
        return itemRepositorySupport.selectDroppedItems();
    }

}