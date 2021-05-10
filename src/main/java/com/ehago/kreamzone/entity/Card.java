package com.ehago.kreamzone.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Card extends BaseTime {
    
    @Id @GeneratedValue
    private Long cardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false, length = 1)
    private String isDefault;

    @Column(nullable = false)
    private String cardNum;

    @Column(nullable = false)
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
