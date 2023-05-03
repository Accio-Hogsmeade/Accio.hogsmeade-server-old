package accio.hogsmeade.store.member.controller.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class EditAddressRequest {

    @NotBlank
    @Size(max = 255)
    @ApiModelProperty(example = "newMainAddress")
    private String mainAddress;
    @NotBlank
    @Size(max = 255)
    @ApiModelProperty(example = "newDetailAddress")
    private String detailAddress;
}
