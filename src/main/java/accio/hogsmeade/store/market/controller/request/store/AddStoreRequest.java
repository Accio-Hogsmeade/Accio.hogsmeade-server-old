package accio.hogsmeade.store.market.controller.request.store;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddStoreRequest {

    @NotBlank
    @Size(max = 100)
    private String storeName;
    @NotBlank
    private String content;
    @NotNull
    private MultipartFile attachFile;
}
