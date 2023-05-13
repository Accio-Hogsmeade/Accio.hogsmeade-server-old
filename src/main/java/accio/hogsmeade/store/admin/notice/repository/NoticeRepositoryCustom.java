package accio.hogsmeade.store.admin.notice.repository;

import accio.hogsmeade.store.admin.controller.response.notice.DetailNoticeResponse;
import accio.hogsmeade.store.admin.controller.response.notice.NoticeResponse;
import accio.hogsmeade.store.admin.notice.repository.dto.NoticeSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface NoticeRepositoryCustom {

    Optional<DetailNoticeResponse> findDetailById(Long noticeId);

    Page<NoticeResponse> findByCondition(NoticeSearchCondition condition, Pageable pageable);

    List<NoticeResponse> findByPin();
}
