package accio.hogsmeade.store.client.controller.request.member;

import accio.hogsmeade.store.client.member.Authority;
import accio.hogsmeade.store.client.member.Identity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class JoinMemberRequest {

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @ApiModelProperty(example = "harry")
    private String loginId;
    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    @ApiModelProperty(example = "abcd1234!")
    private String loginPw;
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
    @Size(max = 255)
    @ApiModelProperty(example = "mainAddress")
    private String mainAddress;
    @NotBlank
    @Size(max = 255)
    @ApiModelProperty(example = "detailAddress")
    private String detailAddress;
    @ApiModelProperty(example = "STUDENT")
    private Identity identity;
    @ApiModelProperty(example = "MEMBER")
    private Authority authority;
}