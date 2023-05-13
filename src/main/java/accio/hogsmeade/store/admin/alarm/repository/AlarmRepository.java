package accio.hogsmeade.store.admin.alarm.repository;

import accio.hogsmeade.store.admin.alarm.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long>, AlarmRepositoryCustom {
}
