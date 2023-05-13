package accio.hogsmeade.store.market.store.service;

import accio.hogsmeade.store.market.store.service.dto.AddStoreDto;
import accio.hogsmeade.store.market.store.service.dto.EditStoreDto;

import javax.transaction.Transactional;

@Transactional
public interface StoreService {

    Long registerStore(String loginId, AddStoreDto dto);

    Long editStore(Long storeId, EditStoreDto dto);
}
