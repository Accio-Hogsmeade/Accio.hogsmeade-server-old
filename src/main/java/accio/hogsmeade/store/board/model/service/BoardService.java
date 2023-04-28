package accio.hogsmeade.store.board.model.service;

import accio.hogsmeade.store.board.model.service.dto.AddBoardDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BoardService {
    Long writeBoard(String loginId, Long categoryId, AddBoardDto addBoardDto);
}
