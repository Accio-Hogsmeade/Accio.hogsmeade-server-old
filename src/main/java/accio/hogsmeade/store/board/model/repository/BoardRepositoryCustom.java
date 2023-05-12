package accio.hogsmeade.store.board.model.repository;

import accio.hogsmeade.store.board.controller.dto.response.BoardResponse;
import accio.hogsmeade.store.board.model.repository.dto.BoardSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {
    Page<BoardResponse> searchByCondition(BoardSearchCondition condition, Pageable pageable);
}
