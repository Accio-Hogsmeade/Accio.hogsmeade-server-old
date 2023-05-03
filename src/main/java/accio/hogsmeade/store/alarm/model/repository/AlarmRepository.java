package accio.hogsmeade.store.alarm.model.repository;

import accio.hogsmeade.store.alarm.model.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long>, AlarmRepositoryCustom {
}
