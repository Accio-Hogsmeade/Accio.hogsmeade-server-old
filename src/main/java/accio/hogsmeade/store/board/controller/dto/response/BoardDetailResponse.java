package accio.hogsmeade.store.board.controller.dto.response;

import accio.hogsmeade.store.board.model.Category;
import accio.hogsmeade.store.common.model.UploadFile;
import accio.hogsmeade.store.member.model.Identity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDetailResponse {
    private Long boardId;
    private String category;
    private String title;
    private String content;
    private int hit;
    private Identity identity;
    private LocalDateTime createdDate;
    private UploadFile uploadFile;
    private Long memberId;

    public BoardDetailResponse(Long boardId, String category, Identity identity, LocalDateTime createdDate, UploadFile uploadFile, Long memberId) {
        this.boardId = boardId;
        this.category = category;
        this.identity = identity;
        this.createdDate = createdDate;
        this.uploadFile = uploadFile;
        this.memberId = memberId;
    }
}
