package accio.hogsmeade.store.board.model.service;

import accio.hogsmeade.store.board.controller.dto.response.BoardDetailResponse;
import accio.hogsmeade.store.board.controller.dto.response.BoardResponse;
import accio.hogsmeade.store.board.model.repository.dto.BoardSearchCondition;
import accio.hogsmeade.store.board.model.service.dto.AddBoardDto;
import accio.hogsmeade.store.board.model.service.dto.EditBoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BoardService {
    Long writeBoard(String loginId, Long categoryId, AddBoardDto addBoardDto);

    Long editBoard(String loginId, Long boardId, EditBoardDto editBoardDto);

    Page<BoardResponse> getBoardList(BoardSearchCondition condition, Pageable pageable);

    BoardDetailResponse getBoard(Long boardId);
}
