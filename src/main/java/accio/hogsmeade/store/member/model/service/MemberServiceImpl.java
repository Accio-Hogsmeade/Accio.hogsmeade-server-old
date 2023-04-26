package accio.hogsmeade.store.member.model.service;

import accio.hogsmeade.store.common.exception.DuplicateException;
import accio.hogsmeade.store.common.model.Address;
import accio.hogsmeade.store.jwt.JwtTokenProvider;
import accio.hogsmeade.store.jwt.TokenInfo;
import accio.hogsmeade.store.member.model.Member;
import accio.hogsmeade.store.member.model.repository.MemberRepository;
import accio.hogsmeade.store.member.model.service.dto.AddMemberDto;
import accio.hogsmeade.store.member.model.service.dto.EditAddressDto;
import accio.hogsmeade.store.member.model.service.dto.EditLoginPwDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

import static accio.hogsmeade.store.member.model.Grade.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Long joinMember(AddMemberDto dto) {
        Optional<Member> loginId = memberRepository.findByLoginId(dto.getLoginId());
        if (loginId.isPresent()) {
            throw new DuplicateException();
        }

        Optional<Member> tel = memberRepository.findByTel(dto.getTel());
        if (tel.isPresent()) {
            throw new DuplicateException();
        }

        Address address = Address.builder()
                .mainAddress(dto.getMainAddress())
                .detailAddress(dto.getDetailAddress())
                .build();
        Member member = Member.builder()
                .loginId(dto.getLoginId())
                .loginPw(dto.getLoginPw())
                .name(dto.getUsername())
                .tel(dto.getTel())
                .address(address)
                .identity(dto.getIdentity())
                .grade(QUAFFLE)
                .roles(Collections.singletonList(dto.getAuthority().toString()))
                .build();

        Member savedMember = memberRepository.save(member);

        return savedMember.getId();
    }

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
    public Long editLoginPw(String loginId, EditLoginPwDto dto) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException();
        }

        Member member = findMember.get();
        member.changeLoginPw(dto.getOldLoginPw(), dto.getNewLoginPw());
        return member.getId();
    }

    @Override
    public Long editTel(String loginId, String newTel) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException();
        }

        Member member = findMember.get();
        member.changeTel(newTel);
        return member.getId();
    }

    @Override
    public Long editAddress(String loginId, EditAddressDto dto) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException();
        }

        Member member = findMember.get();
        member.changeAddress(dto.getMainAddress(), dto.getDetailAddress());
        return member.getId();
    }
}
