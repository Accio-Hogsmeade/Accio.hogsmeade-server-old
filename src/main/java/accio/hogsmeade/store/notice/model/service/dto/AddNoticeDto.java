package accio.hogsmeade.store.notice.model.service.dto;

import lombok.Data;

@Data
public class AddNoticeDto {

    private String title;
    private String content;
    private String pin;
}
