package accio.hogsmeade.store.market.store.service.impl;

import accio.hogsmeade.store.client.member.Member;
import accio.hogsmeade.store.client.member.validator.MemberValidator;
import accio.hogsmeade.store.market.store.repository.StoreRepository;
import accio.hogsmeade.store.market.store.service.StoreService;
import accio.hogsmeade.store.market.store.service.dto.AddStoreDto;
import accio.hogsmeade.store.market.store.Store;
import accio.hogsmeade.store.market.store.service.dto.EditStoreDto;
import accio.hogsmeade.store.market.store.validator.StoreValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final StoreValidator storeValidator;
    private final MemberValidator memberValidator;

    @Override
    public Long registerStore(String loginId, AddStoreDto dto) {
        Member findMember = memberValidator.findByLoginId(loginId);

        Store store = Store.builder()
                .storeName(dto.getStoreName())
                .content(dto.getContent())
                .uploadFile(dto.getUploadFile())
                .member(findMember)
                .build();

        Store savedStore = storeRepository.save(store);
        return savedStore.getId();
    }

    @Override
    public Long editStore(Long storeId, EditStoreDto dto) {
        Store findStore = storeValidator.findById(storeId);
        findStore.changeStore(dto.getContent(), dto.getUploadFile());
        return findStore.getId();
    }
}
