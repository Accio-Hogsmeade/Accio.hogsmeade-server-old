package accio.hogsmeade.store.market.controller;

import accio.hogsmeade.store.common.FileStore;
import accio.hogsmeade.store.common.model.UploadFile;
import accio.hogsmeade.store.jwt.SecurityUtil;
import accio.hogsmeade.store.market.store.service.StoreService;
import accio.hogsmeade.store.market.store.service.dto.AddStoreDto;
import accio.hogsmeade.store.market.controller.request.store.AddStoreRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;
    private final FileStore fileStore;

    @PostMapping("/register")
    public Long registerStore(@Valid @RequestBody AddStoreRequest request) throws IOException {
        String loginId = SecurityUtil.getCurrentLoginId();

        UploadFile uploadFile = fileStore.storeFile(request.getAttachFile());

        AddStoreDto dto = AddStoreDto.builder()
                .storeName(request.getStoreName())
                .content(request.getContent())
                .uploadFile(uploadFile)
                .build();

        return storeService.registerStore(loginId, dto);
    }

}
