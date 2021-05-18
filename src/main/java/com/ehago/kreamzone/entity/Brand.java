package com.ehago.kreamzone.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "kream")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Brand {

    @Id
    @GeneratedValue
    private Long brandId;

    @OneToMany(mappedBy = "brand")
    private List<Item> items = new ArrayList<>();

    private String name;

    private String image;

    private String background;

    @Builder
    private Brand(Long brandId, ArrayList<Item> items, String name, String image, String background) {
        this.brandId = brandId;
        this.items = items;
        this.name = name;
        this.image = image;
        this.background = background;
    }

}
