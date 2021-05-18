package com.ehago.kreamzone.dto;

import com.ehago.kreamzone.entity.Account;
import com.ehago.kreamzone.entity.Card;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    private List<Card> cards;

    private Account account;

    private String email;

    private String password;

    private String name;

    private String profileImage;

    private int size;

    private int point;

    private String emailCheckToken;

}
