package accio.hogsmeade.store.alarm.model.repository;

import accio.hogsmeade.store.alarm.controller.dto.AlarmResponse;
import accio.hogsmeade.store.alarm.controller.dto.DetailAlarmResponse;
import accio.hogsmeade.store.alarm.model.repository.dto.AlarmSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AlarmRepositoryCustom {

    List<AlarmResponse> findBeforeThreeDays(Long memberId);

    Page<AlarmResponse> findPagingByMemberId(Long memberId, AlarmSearchCondition condition, Pageable pageable);

    Optional<DetailAlarmResponse> findByMemberId(Long memberId);
}
