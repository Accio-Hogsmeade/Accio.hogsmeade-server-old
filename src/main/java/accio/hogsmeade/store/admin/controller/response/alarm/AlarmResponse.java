package accio.hogsmeade.store.admin.controller.response.alarm;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlarmResponse {

    private Long alarmId;
    private String title;
    private Boolean open;
    private LocalDateTime createdDate;
}
