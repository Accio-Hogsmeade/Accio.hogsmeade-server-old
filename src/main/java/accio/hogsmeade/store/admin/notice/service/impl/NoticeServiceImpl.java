package accio.hogsmeade.store.admin.notice.service.impl;

import accio.hogsmeade.store.admin.notice.Notice;
import accio.hogsmeade.store.common.exception.AuthorityException;
import accio.hogsmeade.store.client.member.Member;
import accio.hogsmeade.store.client.member.validator.MemberValidator;
import accio.hogsmeade.store.admin.notice.repository.NoticeRepository;
import accio.hogsmeade.store.admin.notice.service.NoticeService;
import accio.hogsmeade.store.admin.notice.service.dto.AddNoticeDto;
import accio.hogsmeade.store.admin.notice.service.dto.EditNoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberValidator memberValidator;

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
        Member findMember = memberValidator.findByLoginId(loginId);
        if (isNotAdmin(findMember)) {
            throw new AuthorityException();
        }
        return findMember;
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
