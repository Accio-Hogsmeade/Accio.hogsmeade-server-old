package accio.hogsmeade.store.member.controller.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class FindLoginPwRequest {

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "^[a-zA-Z가-힣]*$")
    @ApiModelProperty(example = "harrypotter")
    private String name;
    @NotBlank
    @Size(min = 13, max = 13)
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
    @ApiModelProperty(example = "077-1234-1234")
    private String tel;
    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @ApiModelProperty(example = "harry")
    private String loginId;
}
