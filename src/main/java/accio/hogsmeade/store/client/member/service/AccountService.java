package accio.hogsmeade.store.client.member.service;

import accio.hogsmeade.store.jwt.TokenInfo;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AccountService {

    TokenInfo login(String loginId, String loginPw);

    String findLoginId(String name, String tel);

    int findLoginPw(String name, String tel, String loginId);
}
