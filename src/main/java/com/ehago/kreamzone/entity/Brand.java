package com.ehago.kreamzone.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Brand extends BaseTime {

    @Id
    @GeneratedValue
    private Long brandId;

    private String name;

    private String image;

    private String background;

    @Builder
    private Brand(Long brandId, String name, String image, String background) {
        this.brandId = brandId;
        this.name = name;
        this.image = image;
        this.background = background;
    }

}
