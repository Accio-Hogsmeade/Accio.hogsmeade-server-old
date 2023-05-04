package accio.hogsmeade.store.member.model.validator;

import accio.hogsmeade.store.common.model.Address;
import accio.hogsmeade.store.member.model.Member;
import accio.hogsmeade.store.member.model.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.NoSuchElementException;

import static accio.hogsmeade.store.member.model.Grade.QUAFFLE;
import static accio.hogsmeade.store.member.model.Identity.STUDENT;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberValidatorTest {

    @Autowired
    private MemberValidator memberValidator;
    @Autowired
    private MemberRepository memberRepository;

    private Member savedMember;

    @BeforeEach
    void beforeEach() {
        createMember();
    }

    @Test
    @DisplayName("loginId로 회원 조회")
    void findByLoginId() {
        //given

        //when
        Member findMember = memberValidator.findByLoginId(savedMember.getLoginId());

        //then
        assertThat(findMember.getId()).isEqualTo(savedMember.getId());
    }

    @Test
    @DisplayName("loginId로 회원 조회#예외")
    void findByLoginIdException() {
        //given
        //when
        //then
        assertThatThrownBy(() -> memberValidator.findByLoginId("noLoginId"))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("tel로 회원 조회")
    void findByTel() {
        //given

        //when
        Member findMember = memberValidator.findByTel(savedMember.getTel());

        //then
        assertThat(findMember.getId()).isEqualTo(savedMember.getId());
    }

    @Test
    @DisplayName("tel로 회원 조회#예외")
    void findByTelException() {
        //given
        //when
        //then
        assertThatThrownBy(() -> memberValidator.findByTel("010-0000-0000"))
                .isInstanceOf(NoSuchElementException.class);
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
        savedMember = memberRepository.save(member);
    }

}