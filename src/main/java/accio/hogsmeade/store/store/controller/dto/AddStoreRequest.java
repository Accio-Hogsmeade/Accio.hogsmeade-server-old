package accio.hogsmeade.store.store.controller.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddStoreRequest {

    private String storeName;
    private String content;
    private MultipartFile attachFile;
}
