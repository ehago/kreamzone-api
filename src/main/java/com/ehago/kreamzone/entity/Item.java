package com.ehago.kreamzone.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "kream")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item extends BaseTime {

    @Id
    @GeneratedValue
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", foreignKey = @ForeignKey(name = "fk_item_brand"))
    private Brand brand;

    private String name;

    private String image;

    private String modelNum;

    private LocalDate releaseDate;

    private int releasePrice;

    @Builder
    private Item(Long itemId, Brand brand, String name, String image, String modelNum, LocalDate releaseDate, int releasePrice) {
        this.itemId = itemId;
        this.brand = brand;
        this.name = name;
        this.image = image;
        this.modelNum = modelNum;
        this.releaseDate = releaseDate;
        this.releasePrice = releasePrice;
    }

}
