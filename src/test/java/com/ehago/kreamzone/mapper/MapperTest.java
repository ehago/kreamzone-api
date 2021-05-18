package com.ehago.kreamzone.mapper;

import com.ehago.kreamzone.dto.MemberDto;
import com.ehago.kreamzone.entity.Account;
import com.ehago.kreamzone.entity.Card;
import com.ehago.kreamzone.entity.Member;
import com.ehago.kreamzone.enumeration.Role;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MapperTest {

    static Member member;
    static MemberDto memberDto;

    @BeforeAll
    public static void setup() {
        Account account = Account.builder()
                .accountNum("1231311111")
                .build();

        Card shinhanCard = Card.builder()
                .cardNum("1111222233334444")
                .company("shinhan")
                .build();

        Card kookminCard = Card.builder()
                .cardNum("5555666677778888")
                .company("kookmin")
                .build();

        member = Member.builder()
                .memberId(1L)
                .cards(Arrays.asList(shinhanCard, kookminCard))
                .account(account)
                .email("jaehojang@nate.com")
                .password("1111")
                .name("jaeho-jang")
                .profileImage("llll")
                .size(270)
                .point(100)
                .role(Role.USER)
                .emailCheckToken("???")
                .emailCheckTokenGeneratedAt(LocalDate.now())
                .build();

        memberDto = MemberDto.builder()
                .account(account)
                .cards(Arrays.asList(shinhanCard, kookminCard))
                .email("jaehojang@nate.com")
                .password("1111")
                .name("jaeho-jang")
                .profileImage("llll")
                .size(270)
                .point(100)
                .build();
    }

    @Test
    public void toEntity() {
        Member member = MemberMapper.MAPPER.toEntity(memberDto);

        assertThat(member.getName()).isEqualTo(memberDto.getName());
        assertThat(member.getAccount()).isEqualTo(memberDto.getAccount());
        assertThat(member.getMemberId()).isNull();
    }

    @Test
    public void toDto() {
        MemberDto memberDto = MemberMapper.MAPPER.toDto(member);

        assertThat(memberDto.getName()).isEqualTo(member.getName());
        assertThat(memberDto.getAccount()).isEqualTo(member.getAccount());
        assertThat(memberDto.getCards()).hasSize(2);
    }

}
