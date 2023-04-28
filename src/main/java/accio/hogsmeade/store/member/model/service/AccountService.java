package accio.hogsmeade.store.member.model.service;

import accio.hogsmeade.store.jwt.TokenInfo;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AccountService {

    TokenInfo login(String loginId, String loginPw);

    String findLoginId(String name, String tel);
}
