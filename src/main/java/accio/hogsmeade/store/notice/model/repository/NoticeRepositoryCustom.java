package accio.hogsmeade.store.notice.model.repository;

import accio.hogsmeade.store.notice.controller.dto.DetailNoticeResponse;
import accio.hogsmeade.store.notice.controller.dto.NoticeResponse;
import accio.hogsmeade.store.notice.model.repository.dto.NoticeSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface NoticeRepositoryCustom {

    Optional<DetailNoticeResponse> findDetailById(Long noticeId);

    Page<NoticeResponse> findByCondition(NoticeSearchCondition condition, Pageable pageable);

    List<NoticeResponse> findByPin();
}
