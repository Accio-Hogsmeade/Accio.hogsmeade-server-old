package accio.hogsmeade.store.notice.model.repository;

import accio.hogsmeade.store.notice.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
