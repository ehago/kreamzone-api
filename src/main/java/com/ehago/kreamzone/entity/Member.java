package com.ehago.kreamzone.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long memberId;

    @OneToMany(mappedBy = "member")
    private ArrayList<Card> cards = new ArrayList<>();

    @OneToOne(name = "",fetch = FetchType.LAZY)
    private Account account;

    @Column(nullable = false, updatable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column
    private int size;

    @Column
    private int point;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @Column
    private String emailCheckToken;

    @Column
    private LocalDate emailCheckTokenGeneratedAt;

    @Builder
    private Member(Long memberId, ArrayList<Card> cards, String email, String password, String name, int size, int point, Role role, String emailCheckToken, LocalDate emailCheckTokenGeneratedAt) {
        this.memberId = memberId;
        this.cards = cards;
        this.email = email;
        this.password = password;
        this.name = name;
        this.size = size;
        this.point = point;
        this.role = role;
        this.emailCheckToken = emailCheckToken;
        this.emailCheckTokenGeneratedAt = emailCheckTokenGeneratedAt;
    }

}
