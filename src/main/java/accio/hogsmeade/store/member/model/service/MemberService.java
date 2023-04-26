package accio.hogsmeade.store.member.model.service;

import accio.hogsmeade.store.jwt.TokenInfo;
import accio.hogsmeade.store.member.model.service.dto.AddMemberDto;
import accio.hogsmeade.store.member.model.service.dto.EditLoginPwDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {

    Long joinMember(AddMemberDto dto);

    TokenInfo login(String loginId, String loginPw);

    Long editLoginPw(String loginId, EditLoginPwDto dto);

    Long editTel(String loginId, String newTel);
}
