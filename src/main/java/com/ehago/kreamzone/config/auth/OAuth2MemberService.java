package com.ehago.kreamzone.config.auth;

import com.ehago.kreamzone.entity.Member;
import com.ehago.kreamzone.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class OAuth2MemberService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oauth2User = delegate.loadUser(userRequest);
        Map<String, Object> attributes = oauth2User.getAttributes();

        String name = (String) attributes.get("name");
        String email = (String) attributes.get("email");
        String picture = (String) attributes.get("picture");

        Member member = Member.builder()
                .name(name)
                .email(email)
                .profileImage(picture)
                .build();

        saveOrUpdate(member);

        return oauth2User;
    }

    private void saveOrUpdate(Member member) {
        Member newMember = memberRepository.findByEmail(member.getEmail())
                .map(entity -> entity.updateMemberInfo(member.getName(), member.getProfileImage()))
                .orElse(member);

        memberRepository.save(newMember);
    }

}
