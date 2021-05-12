package com.ehago.kreamzone.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(schema = "kream")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account extends BaseTime {

    @Id
    @GeneratedValue
    private Long accountId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String accountNum;

    private String bankCode;

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
