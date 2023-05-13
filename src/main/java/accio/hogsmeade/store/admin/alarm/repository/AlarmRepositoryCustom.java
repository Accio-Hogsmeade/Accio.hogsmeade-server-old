package accio.hogsmeade.store.admin.alarm.repository;

import accio.hogsmeade.store.admin.controller.response.alarm.AlarmResponse;
import accio.hogsmeade.store.admin.controller.response.alarm.DetailAlarmResponse;
import accio.hogsmeade.store.admin.alarm.Alarm;
import accio.hogsmeade.store.admin.alarm.repository.dto.AlarmSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AlarmRepositoryCustom {

    List<AlarmResponse> findBeforeThreeDays(Long memberId);

    Page<AlarmResponse> findPagingByMemberId(Long memberId, AlarmSearchCondition condition, Pageable pageable);

    Optional<DetailAlarmResponse> findByMemberId(Long memberId);

    List<Alarm> findAllByIds(List<Long> alarmIds);
}
