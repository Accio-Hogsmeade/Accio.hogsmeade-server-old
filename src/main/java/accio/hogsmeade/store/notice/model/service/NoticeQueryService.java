package accio.hogsmeade.store.notice.model.service;

import accio.hogsmeade.store.notice.controller.dto.DetailNoticeResponse;
import accio.hogsmeade.store.notice.controller.dto.NoticeResponse;
import accio.hogsmeade.store.notice.model.repository.dto.NoticeSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface NoticeQueryService {

    Page<NoticeResponse> searchByCondition(NoticeSearchCondition condition, Pageable pageable);

    DetailNoticeResponse searchDetail(Long noticeId);
}
