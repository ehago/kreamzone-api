package com.ehago.kreamzone.entity;

import com.ehago.kreamzone.enumeration.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "kream")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long memberId;

    @OneToMany(mappedBy = "member")
    private List<Card> cards = new ArrayList<>();

    @OneToOne(mappedBy = "member")
    private Account account;

    private String email;

    private String password;

    private String name;

    private int size;

    private int point;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String emailCheckToken;

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
