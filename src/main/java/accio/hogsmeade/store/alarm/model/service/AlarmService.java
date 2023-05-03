package accio.hogsmeade.store.alarm.model.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AlarmService {

    Long registerAlarm(Long memberId, String message);

    Long openAlarm(String loginId, Long alarmId);

    int removeAlarms(String loginId, List<Long> alarmIds);
}
