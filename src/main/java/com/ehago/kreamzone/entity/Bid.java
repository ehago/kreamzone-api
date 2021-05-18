package com.ehago.kreamzone.entity;

import com.ehago.kreamzone.enumeration.BidType;
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
public class Bid extends BaseTime {

    @Id
    @GeneratedValue
    private Long bidId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_bid_member"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "fk_bid_item"))
    private Item item;

    @Enumerated(EnumType.STRING)
    private BidType type;

    private String size;

    private String price;

    private LocalDate expirationDate;

    @Builder
    private Bid(Long bidId, Member member, Item item, BidType type, String size, String price, LocalDate expirationDate) {
        this.bidId = bidId;
        this.member = member;
        this.item = item;
        this.type = type;
        this.size = size;
        this.price = price;
        this.expirationDate = expirationDate;
    }

}
