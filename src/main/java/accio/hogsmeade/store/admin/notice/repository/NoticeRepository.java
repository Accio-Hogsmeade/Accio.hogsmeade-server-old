package accio.hogsmeade.store.admin.notice.repository;

import accio.hogsmeade.store.admin.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeRepositoryCustom {
}
