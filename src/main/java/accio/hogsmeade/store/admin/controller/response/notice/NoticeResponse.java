package accio.hogsmeade.store.admin.controller.response.notice;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeResponse {

    private Long noticeId;
    private String title;
    private LocalDateTime createdDate;
}
