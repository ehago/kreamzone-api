package com.ehago.kreamzone.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item extends BaseTime {

    @Id
    @GeneratedValue
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", foreignKey = @ForeignKey(name = "fk_item_brand"))
    private Brand brand;

    @OneToMany(mappedBy = "item")
    private List<Bid> bids = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Bookmark> bookmarks = new ArrayList<>();

    private String korName;

    private String engName;

    private String image;

    private String modelNum;

    private String repColor;

    private LocalDate releaseDate;

    private int releasePrice;

    @Builder
    private Item(Long itemId, Brand brand, List<Bookmark> bookmarks, List<Bid> bids, String korName, String engName, String image, String modelNum, String repColor, LocalDate releaseDate, int releasePrice) {
        this.itemId = itemId;
        this.brand = brand;
        this.bookmarks = bookmarks;
        this.bids = bids;
        this.korName = korName;
        this.engName = engName;
        this.image = image;
        this.modelNum = modelNum;
        this.repColor = repColor;
        this.releaseDate = releaseDate;
        this.releasePrice = releasePrice;
    }

}
