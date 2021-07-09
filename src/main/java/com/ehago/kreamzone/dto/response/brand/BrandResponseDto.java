package com.ehago.kreamzone.dto.response.brand;

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
public class BrandResponseDto {

    private Long brandId;

    private String name;

    private String image;

    private String background;

}
