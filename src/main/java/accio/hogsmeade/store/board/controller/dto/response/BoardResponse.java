package accio.hogsmeade.store.board.controller.dto.response;

import accio.hogsmeade.store.member.model.Identity;
import lombok.Builder;
import lombok.Data;

@Data
public class BoardResponse {
    private Long boardId;
    private String category;
    private String title;
    private int hit;
    private Identity identity;
    private String createdDate;
    private boolean file;

    @Builder
    public BoardResponse(Long boardId, String category, String title, int hit, Identity identity, String createdDate, boolean file) {
        this.boardId = boardId;
        this.category = category;
        this.title = title;
        this.hit = hit;
        this.identity = identity;
        this.createdDate = createdDate;
        this.file = file;
    }
}
