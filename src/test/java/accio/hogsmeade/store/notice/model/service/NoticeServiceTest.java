package accio.hogsmeade.store.notice.model.service;

import accio.hogsmeade.store.common.exception.AuthorityException;
import accio.hogsmeade.store.common.model.Address;
import accio.hogsmeade.store.member.model.Member;
import accio.hogsmeade.store.member.model.repository.MemberRepository;
import accio.hogsmeade.store.notice.model.Notice;
import accio.hogsmeade.store.notice.model.repository.NoticeRepository;
import accio.hogsmeade.store.notice.model.service.dto.AddNoticeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

import static accio.hogsmeade.store.member.model.Grade.QUAFFLE;
import static accio.hogsmeade.store.member.model.Identity.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class NoticeServiceTest {

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private NoticeRepository noticeRepository;
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
                .identity(WIZARD)
                .grade(QUAFFLE)
                .roles(Collections.singletonList("ADMIN"))
                .build();
        savedMember = memberRepository.save(member);
    }

    @Test
    @DisplayName("공지사항 저장")
    void registerNotice() {
        //given
        AddNoticeDto dto = AddNoticeDto.builder()
                .title("공지사항 제목")
                .content("공지사항 내용")
                .pin("0")
                .build();

        //when
        Long noticeId = noticeService.registerNotice(savedMember.getLoginId(), dto);

        //then
        Optional<Notice> findNotice = noticeRepository.findById(noticeId);
        assertThat(findNotice).isPresent();
    }

    @Test
    @DisplayName("공지사항 저장")
    void registerNoticeByMember() {
        //given
        AddNoticeDto dto = AddNoticeDto.builder()
                .title("공지사항 제목")
                .content("공지사항 내용")
                .pin("0")
                .build();

        //when
        //then
        assertThatThrownBy(() -> noticeService.registerNotice("noLoginId", dto))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("공지사항 저장#권한 예외#MEMBER")
    void authorityByMember() {
        //given
        Address address = Address.builder().mainAddress("mainAddress").detailAddress("detailAddress").build();
        Member member = Member.builder()
                .loginId("exception")
                .loginPw("abcd1234!")
                .name("harrypotter")
                .tel("077-1234-9876")
                .address(address)
                .identity(WIZARD)
                .grade(QUAFFLE)
                .roles(Collections.singletonList("MEMBER"))
                .build();
        Member savedMember = memberRepository.save(member);
        AddNoticeDto dto = AddNoticeDto.builder()
                .title("공지사항 제목")
                .content("공지사항 내용")
                .pin("0")
                .build();
        //when
        //then
        assertThatThrownBy(() -> noticeService.registerNotice(savedMember.getLoginId(), dto))
                .isInstanceOf(AuthorityException.class);
    }

    @Test
    @DisplayName("공지사항 저장#권한 예외#STORE")
    void authorityByStore() {
        //given
        Address address = Address.builder().mainAddress("mainAddress").detailAddress("detailAddress").build();
        Member member = Member.builder()
                .loginId("exception")
                .loginPw("abcd1234!")
                .name("harrypotter")
                .tel("077-1234-9876")
                .address(address)
                .identity(WIZARD)
                .grade(QUAFFLE)
                .roles(Collections.singletonList("STORE"))
                .build();
        Member savedMember = memberRepository.save(member);
        AddNoticeDto dto = AddNoticeDto.builder()
                .title("공지사항 제목")
                .content("공지사항 내용")
                .pin("0")
                .build();
        //when
        //then
        assertThatThrownBy(() -> noticeService.registerNotice(savedMember.getLoginId(), dto))
                .isInstanceOf(AuthorityException.class);
    }
}