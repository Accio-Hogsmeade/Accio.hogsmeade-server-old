package accio.hogsmeade.store.board.model.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class BoardSearchCondition {
    private String keyword;
    private String category;

    @Builder
    public BoardSearchCondition(String keyword, String category) {
        this.keyword = keyword;
        this.category = category;
    }
}
