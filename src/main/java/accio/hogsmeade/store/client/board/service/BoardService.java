package accio.hogsmeade.store.client.board.service;

import accio.hogsmeade.store.client.controller.response.board.BoardDetailResponse;
import accio.hogsmeade.store.client.controller.response.board.BoardResponse;
import accio.hogsmeade.store.client.board.repository.dto.BoardSearchCondition;
import accio.hogsmeade.store.client.board.service.dto.AddBoardDto;
import accio.hogsmeade.store.client.board.service.dto.EditBoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BoardService {
    Long writeBoard(String loginId, Long categoryId, AddBoardDto addBoardDto);

    Long editBoard(String loginId, Long boardId, EditBoardDto editBoardDto);

    Page<BoardResponse> getBoardList(BoardSearchCondition condition, Pageable pageable);

    BoardDetailResponse getBoard(Long boardId);

    Long deactiveBoard(String loginId, Long boardId);
}
