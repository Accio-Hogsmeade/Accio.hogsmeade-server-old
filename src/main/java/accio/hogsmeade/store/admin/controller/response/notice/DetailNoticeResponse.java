package accio.hogsmeade.store.admin.controller.response.notice;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DetailNoticeResponse {

    private Long noticeId;
    private String title;
    private String content;
    private LocalDateTime createdDate;
}
