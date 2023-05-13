package accio.hogsmeade.store.client.board.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AddBoardDto {
    private String title;
    private String content;
    private String uploadFileName;

    @Builder
    public AddBoardDto(String title, String content, String uploadFileName) {
        this.title = title;
        this.content = content;
        this.uploadFileName = uploadFileName;
    }
}
