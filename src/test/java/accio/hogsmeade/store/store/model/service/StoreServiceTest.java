package accio.hogsmeade.store.store.model.service;

import accio.hogsmeade.store.common.model.Address;
import accio.hogsmeade.store.common.model.UploadFile;
import accio.hogsmeade.store.member.model.Member;
import accio.hogsmeade.store.member.model.repository.MemberRepository;
import accio.hogsmeade.store.store.model.Store;
import accio.hogsmeade.store.store.model.repository.StoreRepository;
import accio.hogsmeade.store.store.model.service.dto.AddStoreDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

import static accio.hogsmeade.store.member.model.Grade.QUAFFLE;
import static accio.hogsmeade.store.member.model.Identity.WIZARD;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreServiceTest {

    @Autowired
    private StoreService storeService;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private MemberRepository memberRepository;

    private Member savedMember;

    @BeforeEach
    void beforeEach() {
        createMember();
    }

    @Test
    @DisplayName("매장 등록")
    void registerStore() {
        //given
        UploadFile uploadFile = new UploadFile("upload.jpg", "store.jpg");
        AddStoreDto dto = AddStoreDto.builder()
                .storeName("Huneydukes")
                .content("환상적인 과자들을 저렴한 가격에 맛보세요!")
                .uploadFile(uploadFile)
                .build();

        //when
        Long storeId = storeService.registerStore(savedMember.getLoginId(), dto);

        //then
        Optional<Store> findStore = storeRepository.findById(storeId);
        assertThat(findStore).isPresent();
    }

    private void createMember() {
        Address address = Address.builder().mainAddress("mainAddress").detailAddress("detailAddress").build();
        Member member = Member.builder()
                .loginId("harry")
                .loginPw("abcd1234!")
                .name("harrypotter")
                .tel("077-1234-1234")
                .address(address)
                .identity(WIZARD)
                .grade(QUAFFLE)
                .roles(Collections.singletonList("STORE"))
                .build();
        savedMember = memberRepository.save(member);
    }
}