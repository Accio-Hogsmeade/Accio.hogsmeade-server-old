package accio.hogsmeade.store.board.model.repository;


import accio.hogsmeade.store.board.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
