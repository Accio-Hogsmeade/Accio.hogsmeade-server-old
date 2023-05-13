package accio.hogsmeade.store.market.store.repository;

import accio.hogsmeade.store.market.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
