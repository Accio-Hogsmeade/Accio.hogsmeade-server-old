package accio.hogsmeade.store.board.model.repository.dto;

import lombok.Data;

@Data
public class BoardSearchCondition {
    private String keyword;
    private String category;
}
