package accio.hogsmeade.store.client.board.repository;

import accio.hogsmeade.store.client.controller.response.board.BoardDetailResponse;
import accio.hogsmeade.store.client.controller.response.board.BoardResponse;
import accio.hogsmeade.store.client.board.repository.dto.BoardSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {
    Page<BoardResponse> searchByCondition(BoardSearchCondition condition, Pageable pageable);

    BoardDetailResponse searchById(Long boardId);
}
