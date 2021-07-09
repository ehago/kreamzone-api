package com.ehago.kreamzone.dto.response.item;

import com.ehago.kreamzone.dto.response.brand.BrandResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class ItemResponseDto {

    private BrandResponseDto brand;

    private String korName;

    private String engName;

    private String image;

    private String repColor;

    private int releasePrice;

    private int immediatelyPurchasePrice;

    public ItemResponseDto(BrandResponseDto brand, String korName, String engName, String image, String repColor, int releasePrice, int immediatelyPurchasePrice) {
        this.brand = brand;
        this.korName = korName;
        this.engName = engName;
        this.image = image;
        this.repColor = repColor;
        this.releasePrice = releasePrice;
        this.immediatelyPurchasePrice = immediatelyPurchasePrice;
    }

}
