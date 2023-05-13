package accio.hogsmeade.store.admin.notice.service;

import accio.hogsmeade.store.common.exception.AuthorityException;
import accio.hogsmeade.store.common.model.Address;
import accio.hogsmeade.store.client.member.Member;
import accio.hogsmeade.store.client.member.repository.MemberRepository;
import accio.hogsmeade.store.admin.notice.Notice;
import accio.hogsmeade.store.admin.notice.repository.NoticeRepository;
import accio.hogsmeade.store.admin.notice.service.dto.AddNoticeDto;
import accio.hogsmeade.store.admin.notice.service.dto.EditNoticeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

import static accio.hogsmeade.store.client.member.Grade.QUAFFLE;
import static accio.hogsmeade.store.client.member.Identity.*;
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
    private Notice savedNotice;

    @BeforeEach
    void beforeEach() {
        createMember();
        createNotice();
    }

    @Test
    @DisplayName("공지사항 저장")
    void registerNotice() {
        //given
        AddNoticeDto dto = getAddNoticeDto();

        //when
        Long noticeId = noticeService.registerNotice(savedMember.getLoginId(), dto);

        //then
        Optional<Notice> findNotice = noticeRepository.findById(noticeId);
        assertThat(findNotice).isPresent();
    }

    @Test
    @DisplayName("공지사항 저장#권한#MEMBER")
    void authorityByMember() {
        //given
        Member savedMember = getMemberAuthority("MEMBER");
        AddNoticeDto dto = getAddNoticeDto();
        //when
        //then
        assertThatThrownBy(() -> noticeService.registerNotice(savedMember.getLoginId(), dto))
                .isInstanceOf(AuthorityException.class);
    }

    @Test
    @DisplayName("공지사항 저장#권한#STORE")
    void authorityByStore() {
        //given
        Member savedMember = getMemberAuthority("STORE");
        AddNoticeDto dto = getAddNoticeDto();
        //when
        //then
        assertThatThrownBy(() -> noticeService.registerNotice(savedMember.getLoginId(), dto))
                .isInstanceOf(AuthorityException.class);
    }

    @Test
    @DisplayName("공지사항 수정")
    void editNotice() {
        //given
        EditNoticeDto dto = getEditNoticeDto();

        //when
        Long noticeId = noticeService.editNotice(savedMember.getLoginId(), savedNotice.getId(), dto);

        //then
        Notice findNotice = noticeRepository.findById(noticeId).get();
        assertThat(findNotice.getTitle()).isEqualTo(dto.getTitle());
    }

    @Test
    @DisplayName("공지사항 수정#미가입 회원")
    void editNoticeByMember() {
        //given
        EditNoticeDto dto = getEditNoticeDto();

        //when
        //then
        assertThatThrownBy(() -> noticeService.editNotice("noLoginId", savedNotice.getId(), dto))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("공지사항 수정#권한#MEMBER")
    void editNoticeAuthorityByMember() {
        //given
        Member savedMember = getMemberAuthority("MEMBER");
        EditNoticeDto dto = getEditNoticeDto();
        //when
        //then
        assertThatThrownBy(() -> noticeService.editNotice(savedMember.getLoginId(), savedNotice.getId(), dto))
                .isInstanceOf(AuthorityException.class);
    }

    @Test
    @DisplayName("공지사항 수정#권한#STORE")
    void editNoticeAuthorityByStore() {
        //given
        Member savedMember = getMemberAuthority("STORE");
        EditNoticeDto dto = getEditNoticeDto();
        //when
        //then
        assertThatThrownBy(() -> noticeService.editNotice(savedMember.getLoginId(), savedNotice.getId(), dto))
                .isInstanceOf(AuthorityException.class);
    }

    @Test
    @DisplayName("공지사항 수정#미등록 공지")
    void editNoticeNotRegister() {
        //given
        EditNoticeDto dto = getEditNoticeDto();
        //when
        //then
        assertThatThrownBy(() -> noticeService.editNotice(savedMember.getLoginId(), 0L, dto))
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
                .identity(WIZARD)
                .grade(QUAFFLE)
                .roles(Collections.singletonList("ADMIN"))
                .build();
        savedMember = memberRepository.save(member);
    }

    private void createNotice() {
        Notice notice = Notice.builder()
                .title("beforeEach title")
                .content("beforeEach content")
                .pin("0")
                .member(savedMember)
                .build();
        savedNotice = noticeRepository.save(notice);
    }

    private static AddNoticeDto getAddNoticeDto() {
        return AddNoticeDto.builder()
                .title("공지사항 제목")
                .content("공지사항 내용")
                .pin("0")
                .build();
    }

    private Member getMemberAuthority(String authority) {
        Address address = Address.builder().mainAddress("mainAddress").detailAddress("detailAddress").build();
        Member member = Member.builder()
                .loginId("exception")
                .loginPw("abcd1234!")
                .name("harrypotter")
                .tel("077-1234-9876")
                .address(address)
                .identity(WIZARD)
                .grade(QUAFFLE)
                .roles(Collections.singletonList(authority))
                .build();
        return memberRepository.save(member);
    }

    private static EditNoticeDto getEditNoticeDto() {
        return EditNoticeDto.builder()
                .title("new notice title")
                .content("new notice content")
                .pin("1")
                .build();
    }
}