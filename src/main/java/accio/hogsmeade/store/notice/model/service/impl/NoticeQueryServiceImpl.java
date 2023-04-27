package accio.hogsmeade.store.notice.model.service.impl;

import accio.hogsmeade.store.notice.controller.dto.NoticeResponse;
import accio.hogsmeade.store.notice.model.repository.NoticeRepository;
import accio.hogsmeade.store.notice.model.repository.dto.NoticeSearchCondition;
import accio.hogsmeade.store.notice.model.service.NoticeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeQueryServiceImpl implements NoticeQueryService {

    private final NoticeRepository noticeRepository;

    @Override
    public Page<NoticeResponse> searchByCondition(NoticeSearchCondition condition, Pageable pageable) {
        return noticeRepository.findByCondition(condition, pageable);
    }
}
