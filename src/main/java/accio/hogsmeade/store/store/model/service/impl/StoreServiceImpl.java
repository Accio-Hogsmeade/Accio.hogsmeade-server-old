package accio.hogsmeade.store.store.model.service.impl;

import accio.hogsmeade.store.member.model.repository.MemberRepository;
import accio.hogsmeade.store.store.model.repository.StoreRepository;
import accio.hogsmeade.store.store.model.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;


}
