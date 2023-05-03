package accio.hogsmeade.store.alarm.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class AlarmTest {

    @Test
    @DisplayName("열람 상태 변경")
    void openAlarm() {
        //given
        Alarm alarm = Alarm.builder()
                .open(true)
                .build();

        //when
        alarm.alarmOpen();

        //then
        assertThat(alarm.getOpen()).isTrue();
    }
}