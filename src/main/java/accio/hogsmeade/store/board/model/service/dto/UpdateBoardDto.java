package accio.hogsmeade.store.board.model.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdateBoardDto {
    private String title;
    private String content;
    private String uploadFileName;

    @Builder
    public UpdateBoardDto(String title, String content, String uploadFileName) {
        this.title = title;
        this.content = content;
        this.uploadFileName = uploadFileName;
    }
}
