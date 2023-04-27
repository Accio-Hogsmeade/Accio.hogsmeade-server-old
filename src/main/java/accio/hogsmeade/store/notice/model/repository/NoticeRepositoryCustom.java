package accio.hogsmeade.store.notice.model.repository;

import accio.hogsmeade.store.notice.model.Notice;
import accio.hogsmeade.store.notice.model.repository.dto.NoticeSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeRepositoryCustom {

    Page<Notice> findByCondition(NoticeSearchCondition condition, Pageable pageable);
}
