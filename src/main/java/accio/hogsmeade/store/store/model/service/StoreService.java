package accio.hogsmeade.store.store.model.service;

import accio.hogsmeade.store.store.model.service.dto.AddStoreDto;
import accio.hogsmeade.store.store.model.service.dto.EditStoreDto;

import javax.transaction.Transactional;

@Transactional
public interface StoreService {

    Long registerStore(String loginId, AddStoreDto dto);

    Long editStore(Long storeId, EditStoreDto dto);
}
