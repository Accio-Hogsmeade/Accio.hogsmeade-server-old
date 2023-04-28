package accio.hogsmeade.store.member.controller.dto;

import accio.hogsmeade.store.member.model.Authority;
import accio.hogsmeade.store.member.model.Identity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class JoinMemberRequest {

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String loginId;
    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    private String loginPw;
    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "^[a-zA-Z가-힣]*$")
    private String name;
    @NotBlank
    @Size(min = 13, max = 13)
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
    private String tel;
    @NotBlank
    @Size(max = 255)
    private String mainAddress;
    @NotBlank
    @Size(max = 255)
    private String detailAddress;
    private Identity identity;
    private Authority authority;
}