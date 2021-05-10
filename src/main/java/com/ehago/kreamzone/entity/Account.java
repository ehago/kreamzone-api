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
public class Account extends BaseTime {
    
    @Id @GeneratedValue
    private Long accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String accountNum;

    @Column(nullable = false, length = 3)
    private String bankCode;

    @Column(nullable = false)
    private String holderName;

    @Builder
    private Account(Long accountId, Member member, String accountNum, String bankCode, String holderName) {
        this.accountId = accountId;
        this.member = member;
        this.accountNum = accountNum;
        this.bankCode = bankCode;
        this.holderName = holderName;
    }

}
