package accio.hogsmeade.store.admin.notice.service;

import accio.hogsmeade.store.admin.controller.response.notice.DetailNoticeResponse;
import accio.hogsmeade.store.admin.controller.response.notice.NoticeResponse;
import accio.hogsmeade.store.admin.notice.repository.dto.NoticeSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface NoticeQueryService {

    Page<NoticeResponse> searchByCondition(NoticeSearchCondition condition, Pageable pageable);

    DetailNoticeResponse searchDetail(Long noticeId);
}
