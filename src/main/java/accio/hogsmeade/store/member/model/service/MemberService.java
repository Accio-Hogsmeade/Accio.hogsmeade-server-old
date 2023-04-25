package accio.hogsmeade.store.member.model.service;

import accio.hogsmeade.store.member.model.service.dto.AddMemberDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {

    Long joinMember(AddMemberDto dto);
}
