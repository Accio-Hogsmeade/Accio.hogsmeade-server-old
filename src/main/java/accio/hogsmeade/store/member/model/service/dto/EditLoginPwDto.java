package accio.hogsmeade.store.member.model.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class EditLoginPwDto {

    private String oldLoginPw;
    private String newLoginPw;

    @Builder
    public EditLoginPwDto(String oldLoginPw, String newLoginPw) {
        this.oldLoginPw = oldLoginPw;
        this.newLoginPw = newLoginPw;
    }
}
