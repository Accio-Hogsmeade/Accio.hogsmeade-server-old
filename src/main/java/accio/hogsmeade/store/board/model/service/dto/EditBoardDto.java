package accio.hogsmeade.store.board.model.service.dto;

import accio.hogsmeade.store.common.model.UploadFile;
import lombok.Builder;
import lombok.Data;

@Data
public class EditBoardDto {
    private Long categoryId;
    private String title;
    private String content;
    private String uploadFileName;

    @Builder
    public EditBoardDto(Long categoryId, String title, String content, String uploadFileName) {
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.uploadFileName = uploadFileName;
    }
}
