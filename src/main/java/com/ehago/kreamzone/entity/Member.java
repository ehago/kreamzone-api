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
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseTime {

    @Id
    @GeneratedValue
    private Long memberId;

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
    private Member(Long memberId, String email, String password, String name, String profileImage, int size, int point, Role role, String emailCheckToken, LocalDate emailCheckTokenGeneratedAt) {
        this.memberId = memberId;
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

    public Member updateMemberInfo(String name, String profile) {
        this.name = name;
        this.profileImage = profile;

        return this;
    }

}
