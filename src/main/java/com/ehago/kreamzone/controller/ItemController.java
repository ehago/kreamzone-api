package com.ehago.kreamzone.controller;

import com.ehago.kreamzone.dto.response.item.ItemResponseDto;
import com.ehago.kreamzone.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/dropped")
    public ResponseEntity<?> getDroppedItems() {
        List<ItemResponseDto> items = itemService.getDroppedItems();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/popular")
    public ResponseEntity<?> getPopularItems() {
        List<ItemResponseDto> items = itemService.getPopularItems();
        return ResponseEntity.ok().body(items);
    }

}
