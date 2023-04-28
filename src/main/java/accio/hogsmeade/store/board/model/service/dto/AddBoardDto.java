package accio.hogsmeade.store.board.model.service.dto;

import accio.hogsmeade.store.common.model.UploadFile;
import lombok.Builder;
import lombok.Data;

@Data
public class AddBoardDto {
    private String title;
    private String content;
    private UploadFile uploadFile;

    @Builder
    public AddBoardDto(String title, String content, UploadFile uploadFile) {
        this.title = title;
        this.content = content;
        this.uploadFile = uploadFile;
    }
}
