package accio.hogsmeade.store.admin.controller.response.alarm;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.*;

@Data
@NoArgsConstructor(access = PROTECTED)
public class DetailAlarmResponse {

    private Long alarmId;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    @Builder
    public DetailAlarmResponse(Long alarmId, String title, String content, LocalDateTime createdDate) {
        this.alarmId = alarmId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }
}
