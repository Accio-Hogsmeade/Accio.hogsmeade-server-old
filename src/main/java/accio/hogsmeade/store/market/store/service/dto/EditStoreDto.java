package accio.hogsmeade.store.market.store.service.dto;

import accio.hogsmeade.store.common.model.UploadFile;
import lombok.Builder;
import lombok.Data;

@Data
public class EditStoreDto {

    private String content;
    private UploadFile uploadFile;

    @Builder
    public EditStoreDto(String content, UploadFile uploadFile) {
        this.content = content;
        this.uploadFile = uploadFile;
    }
}
