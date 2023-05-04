package accio.hogsmeade.store.store.model.service.impl;

import accio.hogsmeade.store.member.model.Member;
import accio.hogsmeade.store.member.model.repository.MemberRepository;
import accio.hogsmeade.store.member.model.validator.MemberValidator;
import accio.hogsmeade.store.store.model.Store;
import accio.hogsmeade.store.store.model.repository.StoreRepository;
import accio.hogsmeade.store.store.model.service.StoreService;
import accio.hogsmeade.store.store.model.service.dto.AddStoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
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
}
