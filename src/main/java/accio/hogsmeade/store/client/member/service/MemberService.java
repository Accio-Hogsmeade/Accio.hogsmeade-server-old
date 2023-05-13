package accio.hogsmeade.store.client.member.service;

import accio.hogsmeade.store.client.member.service.dto.AddMemberDto;
import accio.hogsmeade.store.client.member.service.dto.EditAddressDto;
import accio.hogsmeade.store.client.member.service.dto.EditLoginPwDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {

    Long joinMember(AddMemberDto dto);

    Long editLoginPw(String loginId, EditLoginPwDto dto);

    Long editTel(String loginId, String newTel);

    Long editAddress(String loginId, EditAddressDto dto);

    Long withdrawal(String loginId, String loginPw);
}
