package accio.hogsmeade.store.member.model.service.dto;

import accio.hogsmeade.store.member.model.Authority;
import accio.hogsmeade.store.member.model.Identity;
import lombok.Builder;
import lombok.Data;

@Data
public class AddMemberDto {

    private String loginId;
    private String loginPw;
    private String name;
    private String tel;
    private String mainAddress;
    private String detailAddress;
    private Identity identity;
    private Authority authority;

    @Builder
    public AddMemberDto(String loginId, String loginPw, String name, String tel, String mainAddress, String detailAddress, Identity identity, Authority authority) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.tel = tel;
        this.mainAddress = mainAddress;
        this.detailAddress = detailAddress;
        this.identity = identity;
        this.authority = authority;
    }
}
