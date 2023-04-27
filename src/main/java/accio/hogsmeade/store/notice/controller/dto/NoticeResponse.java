package accio.hogsmeade.store.notice.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeResponse {

    private Long noticeId;
    private String title;
    private LocalDateTime createdDate;
}
