package accio.hogsmeade.store.notice.model.service.impl;

import accio.hogsmeade.store.common.exception.AuthorityException;
import accio.hogsmeade.store.member.model.Member;
import accio.hogsmeade.store.member.model.repository.MemberRepository;
import accio.hogsmeade.store.notice.model.Notice;
import accio.hogsmeade.store.notice.model.repository.NoticeRepository;
import accio.hogsmeade.store.notice.model.service.NoticeService;
import accio.hogsmeade.store.notice.model.service.dto.AddNoticeDto;
import accio.hogsmeade.store.notice.model.service.dto.EditNoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long registerNotice(String loginId, AddNoticeDto dto) {
        Member member = validateMember(loginId);

        Notice notice = createNoticeDomain(dto, member);
        Notice savedNotice = noticeRepository.save(notice);
        return savedNotice.getId();
    }

    @Override
    public Long editNotice(String loginId, Long noticeId, EditNoticeDto dto) {
        validateMember(loginId);

        Optional<Notice> findNotice = noticeRepository.findById(noticeId);
        if (findNotice.isEmpty()) {
            throw new NoSuchElementException();
        }

        Notice notice = findNotice.get();
        notice.changeNotice(dto.getTitle(), dto.getContent(), dto.getPin());
        return notice.getId();
    }

    private Member validateMember(String loginId) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException();
        }

        Member member = findMember.get();
        if (isNotAdmin(member)) {
            throw new AuthorityException();
        }
        return member;
    }

    private boolean isNotAdmin(Member member) {
        return !member.getRoles().get(0).equals("ADMIN");
    }

    private Notice createNoticeDomain(AddNoticeDto dto, Member member) {
        return Notice.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .pin(dto.getPin())
                .member(member)
                .build();
    }
}
