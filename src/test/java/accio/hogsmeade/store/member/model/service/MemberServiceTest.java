package accio.hogsmeade.store.member.model.service;

import accio.hogsmeade.store.common.model.Address;
import accio.hogsmeade.store.member.model.Member;
import accio.hogsmeade.store.member.model.repository.MemberRepository;
import accio.hogsmeade.store.member.model.service.dto.EditLoginPwDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

import static accio.hogsmeade.store.member.model.Grade.*;
import static accio.hogsmeade.store.member.model.Identity.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    
    @Test
    @DisplayName("비밀번호 수정")
    void editLoginPw() {
        //given
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
        Member savedMember = memberRepository.save(member);
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
    @DisplayName("비밀번호 수정#미가입회원")
    void editLoginPwByMember() {
        //given
        EditLoginPwDto dto = EditLoginPwDto.builder()
                .oldLoginPw("abcd1234!")
                .newLoginPw("qwer1234@")
                .build();
        //when
        //then
        assertThatThrownBy(() -> memberService.editLoginPw("notLoginId", dto))
                .isInstanceOf(NoSuchElementException.class);
    }
}
