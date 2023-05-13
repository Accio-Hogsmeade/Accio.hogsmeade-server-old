package accio.hogsmeade.store.client.member.service;

import accio.hogsmeade.store.common.exception.AuthorityException;
import accio.hogsmeade.store.common.model.Address;
import accio.hogsmeade.store.client.member.Member;
import accio.hogsmeade.store.client.member.repository.MemberRepository;
import accio.hogsmeade.store.client.member.service.dto.EditAddressDto;
import accio.hogsmeade.store.client.member.service.dto.EditLoginPwDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

import static accio.hogsmeade.store.common.model.Active.*;
import static accio.hogsmeade.store.client.member.Grade.*;
import static accio.hogsmeade.store.client.member.Identity.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    private Member savedMember;

    @BeforeEach
    void beforeEach() {
        Address address = Address.builder().mainAddress("mainAddress").detailAddress("detailAddress").build();
        Member member = Member.builder()
                .loginId("harry")
                .loginPw("abcd1234!")
                .name("harrypotter")
                .tel("077-1234-1234")
                .address(address)
                .identity(STUDENT)
                .grade(QUAFFLE)
                .roles(Collections.singletonList("MEMBER"))
                .build();
        savedMember = memberRepository.save(member);
    }

    @Test
    @DisplayName("비밀번호 수정")
    void editLoginPw() {
        //given
        EditLoginPwDto dto = EditLoginPwDto.builder()
                .oldLoginPw("abcd1234!")
                .newLoginPw("qwer1234@")
                .build();
        //when
        Long memberId = memberService.editLoginPw(savedMember.getLoginId(), dto);

        //then
        Optional<Member> findMember = memberRepository.findById(memberId);
        assertThat(findMember.get().getLoginPw()).isEqualTo(dto.getNewLoginPw());
    }

    @Test
    @DisplayName("연락처 수정")
    void editTel() {
        //given
        String newTel = "010-1234-5678";

        //when
        Long memberId = memberService.editTel(savedMember.getLoginId(), newTel);

        //then
        Optional<Member> findMember = memberRepository.findById(memberId);
        assertThat(findMember.get().getTel()).isEqualTo(newTel);
    }

    @Test
    @DisplayName("주소 수정")
    void editAddress() {
        //given
        EditAddressDto dto = EditAddressDto.builder()
                .mainAddress("newMainAddress")
                .detailAddress("newDetailAddress")
                .build();
        //when
        Long memberId = memberService.editAddress(savedMember.getLoginId(), dto);

        //then
        Optional<Member> findMember = memberRepository.findById(memberId);
        assertThat(findMember.get().getAddress().getMainAddress()).isEqualTo(dto.getMainAddress());
    }

    @Test
    @DisplayName("회원 탈퇴")
    void withdrawal() {
        //given

        //when
        Long memberId = memberService.withdrawal(savedMember.getLoginId(), savedMember.getLoginPw());

        //then
        Member findMember = memberRepository.findById(memberId).get();
        assertThat(findMember.getActive()).isEqualTo(DEACTIVE);
    }
    
    @Test
    @DisplayName("회원 탈퇴#비밀번호 불일치")
    void withdrawalNotEqualLoginPw() {
        //given
            
        //when
        //then
        assertThatThrownBy(() -> memberService.withdrawal(savedMember.getLoginId(), "notLoginPw"))
                .isInstanceOf(AuthorityException.class);
    }
}
