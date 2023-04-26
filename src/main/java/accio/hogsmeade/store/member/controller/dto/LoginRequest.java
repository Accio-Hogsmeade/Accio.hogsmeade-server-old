package accio.hogsmeade.store.member.controller.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String loginId;
    private String loginPw;
}
