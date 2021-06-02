package com.ehago.kreamzone.entity;

import com.ehago.kreamzone.enumeration.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseTime {

    @Id
    @GeneratedValue
    private Long memberId;

    @OneToMany(mappedBy = "member")
    private List<Card> cards = new ArrayList<>();

    @OneToOne(mappedBy = "member")
    private Account account;

    private String email;

    private String password;

    private String name;

    private String profileImage;

    private int size;

    private int point;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String emailCheckToken;

    private LocalDate emailCheckTokenGeneratedAt;

    @Builder
    private Member(Long memberId, List<Card> cards, Account account, String email, String password, String name, String profileImage, int size, int point, Role role, String emailCheckToken, LocalDate emailCheckTokenGeneratedAt) {
        this.memberId = memberId;
        this.cards = cards;
        this.account = account;
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImage = profileImage;
        this.size = size;
        this.point = point;
        this.role = role;
        this.emailCheckToken = emailCheckToken;
        this.emailCheckTokenGeneratedAt = emailCheckTokenGeneratedAt;
    }

}
