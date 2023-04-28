package accio.hogsmeade.store.member.controller.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class EditAddressRequest {

    @NotBlank
    @Size(max = 255)
    private String mainAddress;
    @NotBlank
    @Size(max = 255)
    private String detailAddress;
}
