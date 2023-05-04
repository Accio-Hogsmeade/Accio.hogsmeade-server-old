package accio.hogsmeade.store.member.model.service.impl;

import accio.hogsmeade.store.common.exception.FindAccountException;
import accio.hogsmeade.store.jwt.JwtTokenProvider;
import accio.hogsmeade.store.jwt.TokenInfo;
import accio.hogsmeade.store.member.model.Member;
import accio.hogsmeade.store.member.model.service.AccountService;
import accio.hogsmeade.store.member.model.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberValidator memberValidator;

    @Override
    public TokenInfo login(String loginId, String loginPw) {
        //loginId, loginPw를 기반으로 Authentication 객체 생성
        //authentication는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginId, loginPw);

        //실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        //authenticate 메서드가 실행될 때 CustomUserDetailsService에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //인증 정보를 기반으로 JWT 토큰 생성
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String findLoginId(String name, String tel) {
        Member member = memberValidator.findByTel(tel);

        if (isNotEqualName(member, name)) {
            throw new FindAccountException();
        }

        return member.getLoginId();
    }

    @Override
    public int findLoginPw(String name, String tel, String loginId) {
        Member member = memberValidator.findByTel(tel);

        if (isNotEqualName(member, name)) {
            throw new FindAccountException();
        }

        if (isNotEqualLoginId(member, loginId)) {
            throw new FindAccountException();
        }

        return 1;
    }

    private boolean isNotEqualName(Member member, String name) {
        return !member.getName().equals(name);
    }

    private boolean isNotEqualLoginId(Member member, String loginId) {
        return !member.getLoginId().equals(loginId);
    }
}
