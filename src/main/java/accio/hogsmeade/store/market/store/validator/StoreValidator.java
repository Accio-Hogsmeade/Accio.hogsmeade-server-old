package accio.hogsmeade.store.market.store.validator;

import accio.hogsmeade.store.market.store.repository.StoreRepository;
import accio.hogsmeade.store.market.store.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.NoSuchElementException;

@Configuration
@RequiredArgsConstructor
public class StoreValidator {

    private final StoreRepository storeRepository;

    public Store findById(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
