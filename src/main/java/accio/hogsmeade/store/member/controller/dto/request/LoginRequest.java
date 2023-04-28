package accio.hogsmeade.store.member.controller.dto.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String loginId;
    private String loginPw;
}
