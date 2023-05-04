package accio.hogsmeade.store.store.model.service.impl;

import accio.hogsmeade.store.member.model.Member;
import accio.hogsmeade.store.member.model.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    @Override
    public Long registerStore(String loginId, AddStoreDto dto) {
        Member findMember = memberRepository.findByLoginId(loginId)
                .orElseThrow(NoSuchElementException::new);

        Store store = Store.builder()
                .storeName(dto.getStoreName())
                .content(dto.getContent())
                .uploadFile(dto.getUploadFile())
                .build();

        Store savedStore = storeRepository.save(store);
        return savedStore.getId();
    }
}
