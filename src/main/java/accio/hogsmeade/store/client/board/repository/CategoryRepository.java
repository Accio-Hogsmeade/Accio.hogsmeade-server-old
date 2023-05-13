package accio.hogsmeade.store.client.board.repository;


import accio.hogsmeade.store.client.board.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
