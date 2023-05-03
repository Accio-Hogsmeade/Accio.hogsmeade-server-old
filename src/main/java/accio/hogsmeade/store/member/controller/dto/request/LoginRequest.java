package accio.hogsmeade.store.member.controller.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginRequest {

    @ApiModelProperty(example = "harry")
    private String loginId;
    @ApiModelProperty(example = "abcd1234!")
    private String loginPw;
}
