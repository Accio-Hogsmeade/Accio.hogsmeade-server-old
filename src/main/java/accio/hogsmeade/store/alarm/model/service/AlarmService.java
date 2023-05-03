package accio.hogsmeade.store.alarm.model.service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AlarmService {

    Long registerAlarm(Long memberId, String message);

    Long openAlarm(String loginId, Long alarmId);
}
