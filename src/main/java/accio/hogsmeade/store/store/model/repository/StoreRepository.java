package accio.hogsmeade.store.store.model.repository;

import accio.hogsmeade.store.store.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
