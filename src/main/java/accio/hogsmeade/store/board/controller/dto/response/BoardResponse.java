package accio.hogsmeade.store.board.controller.dto.response;

import accio.hogsmeade.store.common.model.UploadFile;
import accio.hogsmeade.store.member.model.Identity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class BoardResponse {
    private Long boardId;
    private String category;
    private String title;
    private int hit;
    private Identity identity;
    private LocalDateTime createdDate;
    private boolean file;

    public BoardResponse() {
    }
    public BoardResponse(Long boardId, String category, String title, int hit, Identity identity, LocalDateTime createdDate, UploadFile file) {
        this.boardId = boardId;
        this.category = category;
        this.title = title;
        this.hit = hit;
        this.identity = identity;
//        this.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.createdDate = createdDate;
        this.file = file != null;
    }
}
