package accio.hogsmeade.store.store.model.validator;

import accio.hogsmeade.store.store.model.Store;
import accio.hogsmeade.store.store.model.repository.StoreRepository;
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
