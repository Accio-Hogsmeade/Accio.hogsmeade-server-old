package accio.hogsmeade.store.client.member.service;

import accio.hogsmeade.store.common.exception.FindAccountException;
import accio.hogsmeade.store.common.model.Address;
import accio.hogsmeade.store.client.member.Member;
import accio.hogsmeade.store.client.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static accio.hogsmeade.store.client.member.Grade.QUAFFLE;
import static accio.hogsmeade.store.client.member.Identity.STUDENT;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountServiceTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        createMember();
    }

    @Test
    @DisplayName("아이디 찾기")
    void findLoginId() {
        //given
        String name = "harrypotter";
        String tel = "077-1234-1234";

        //when
        String loginId = accountService.findLoginId(name, tel);

        //then
        Member findMember = memberRepository.findByLoginId(loginId).get();
        assertThat(findMember.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("아이디 찾기#존재하지 않는 연락처")
    void findLoginIdNotExistTel() {
        //given
        String name = "harrypotter";
        String tel = "077-5678-5678";

        //when
        //then
        assertThatThrownBy(() -> accountService.findLoginId(name, tel))
                .isInstanceOf(FindAccountException.class);
    }

    @Test
    @DisplayName("아이디 찾기#회원명 불일치")
    void findLoginIdNotEqualName() {
        //given
        String name = "lion";
        String tel = "077-1234-1234";

        //when
        //then
        assertThatThrownBy(() -> accountService.findLoginId(name, tel))
                .isInstanceOf(FindAccountException.class);
    }

    @Test
    @DisplayName("비밀번호 찾기")
    void findLoginPw() {
        //given
        String name = "harrypotter";
        String tel = "077-1234-1234";
        String loginId = "harry";

        //when
        int result = accountService.findLoginPw(name, tel, loginId);

        //then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("비밀번호 찾기#존재하지 않는 연락처")
    void findLoginPwNotExistTel() {
        //given
        String name = "harrypotter";
        String tel = "077-5678-5678";
        String loginId = "harry";

        //when
        //then
        assertThatThrownBy(() -> accountService.findLoginPw(name, tel, loginId))
                .isInstanceOf(FindAccountException.class);
    }

    @Test
    @DisplayName("비밀번호 찾기#회원명 불일치")
    void findLoginPwNotEqualName() {
        //given
        String name = "lion";
        String tel = "077-1234-1234";
        String loginId = "harry";

        //when
        //then
        assertThatThrownBy(() -> accountService.findLoginPw(name, tel, loginId))
                .isInstanceOf(FindAccountException.class);
    }

    @Test
    @DisplayName("비밀번호 찾기#아이디 불일치")
    void findLoginPwNotEqualLoginId() {
        //given
        String name = "harrypotter";
        String tel = "077-1234-1234";
        String loginId = "noLoginId";

        //when
        //then
        assertThatThrownBy(() -> accountService.findLoginPw(name, tel, loginId))
                .isInstanceOf(FindAccountException.class);
    }

    private void createMember() {
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
        memberRepository.save(member);
    }
}