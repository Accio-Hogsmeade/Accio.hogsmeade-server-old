package accio.hogsmeade.store.alarm.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlarmResponse {

    private Long alarmId;
    private String title;
    private Boolean open;
    private LocalDateTime createdDate;
}
