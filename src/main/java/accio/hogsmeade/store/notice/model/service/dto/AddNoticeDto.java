package accio.hogsmeade.store.notice.model.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AddNoticeDto {

    private String title;
    private String content;
    private String pin;

    @Builder
    public AddNoticeDto(String title, String content, String pin) {
        this.title = title;
        this.content = content;
        this.pin = pin;
    }
}
