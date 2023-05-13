package accio.hogsmeade.store.admin.notice.service.impl;

import accio.hogsmeade.store.admin.notice.repository.dto.NoticeSearchCondition;
import accio.hogsmeade.store.admin.notice.service.NoticeQueryService;
import accio.hogsmeade.store.admin.controller.response.notice.DetailNoticeResponse;
import accio.hogsmeade.store.admin.controller.response.notice.NoticeResponse;
import accio.hogsmeade.store.admin.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeQueryServiceImpl implements NoticeQueryService {

    private final NoticeRepository noticeRepository;

    @Override
    public Page<NoticeResponse> searchByCondition(NoticeSearchCondition condition, Pageable pageable) {
        return noticeRepository.findByCondition(condition, pageable);
    }

    @Override
    public DetailNoticeResponse searchDetail(Long noticeId) {
        Optional<DetailNoticeResponse> findNotice = noticeRepository.findDetailById(noticeId);
        if (findNotice.isEmpty()) {
            throw new NoSuchElementException();
        }
        return findNotice.get();
    }
}
