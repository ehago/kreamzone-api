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

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Card extends BaseTime {

    @Id
    @GeneratedValue
    private Long cardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_card_member"))
    private Member member;

    private String isDefault;

    private String cardNum;

    private String company;

    @Builder
    private Card(Long cardId, Member member, String isDefault, String cardNum, String company) {
        this.cardId = cardId;
        this.member = member;
        this.isDefault = isDefault;
        this.cardNum = cardNum;
        this.company = company;
    }

}
