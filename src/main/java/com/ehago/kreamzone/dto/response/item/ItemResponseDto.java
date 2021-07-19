package com.ehago.kreamzone.dto.response.item;

import com.ehago.kreamzone.dto.response.brand.BrandResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponseDto {

    private Long itemId;

    private BrandResponseDto brand;

    private String engName;

    private String image;

    private int immediatelyPurchasePrice;

}