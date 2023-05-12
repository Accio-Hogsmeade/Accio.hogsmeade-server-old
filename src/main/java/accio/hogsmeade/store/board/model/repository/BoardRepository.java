package accio.hogsmeade.store.board.model.repository;

import accio.hogsmeade.store.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
}
