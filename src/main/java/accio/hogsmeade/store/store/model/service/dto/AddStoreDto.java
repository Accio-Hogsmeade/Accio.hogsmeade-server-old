package accio.hogsmeade.store.store.model.service.dto;

import accio.hogsmeade.store.common.model.UploadFile;
import lombok.Builder;
import lombok.Data;

@Data
public class AddStoreDto {

    private String storeName;
    private String content;
    private UploadFile uploadFile;

    @Builder
    public AddStoreDto(String storeName, String content, UploadFile uploadFile) {
        this.storeName = storeName;
        this.content = content;
        this.uploadFile = uploadFile;
    }
}
