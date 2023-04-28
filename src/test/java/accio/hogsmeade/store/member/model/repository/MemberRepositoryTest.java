package accio.hogsmeade.store.member.model.repository;

import accio.hogsmeade.store.common.model.Address;
import accio.hogsmeade.store.member.model.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

import static accio.hogsmeade.store.member.model.Grade.QUAFFLE;
import static accio.hogsmeade.store.member.model.Identity.STUDENT;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        createMember();
    }

    @Test
    @DisplayName("로그인 아이디로 조회")
    void findByLoginId() {
        //given

        //when
        Optional<Member> findMember = memberRepository.findByLoginId("harry");

        //then
        assertThat(findMember).isPresent();
    }

    @Test
    @DisplayName("연락처로 조회")
    void findByTel() {
        //given

        //when
        Optional<Member> findMember = memberRepository.findByTel("077-1234-1234");

        //then
        assertThat(findMember).isPresent();
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