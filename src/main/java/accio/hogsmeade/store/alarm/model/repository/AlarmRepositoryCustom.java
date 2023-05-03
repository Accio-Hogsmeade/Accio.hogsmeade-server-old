package accio.hogsmeade.store.alarm.model.repository;

import accio.hogsmeade.store.alarm.controller.dto.PreviewAlarmResponse;

import java.util.List;

public interface AlarmRepositoryCustom {

    List<PreviewAlarmResponse> findBeforeThreeDays(Long memberId);
}
