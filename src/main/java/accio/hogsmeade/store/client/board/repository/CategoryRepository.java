package accio.hogsmeade.store.client.board.repository;


import accio.hogsmeade.store.client.board.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(@Param("name") String name);
}
