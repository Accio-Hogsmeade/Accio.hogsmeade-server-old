package accio.hogsmeade.store.member.model.service.impl;

import accio.hogsmeade.store.common.exception.AuthorityException;
import accio.hogsmeade.store.common.exception.DuplicateException;
import accio.hogsmeade.store.common.model.Address;
import accio.hogsmeade.store.member.model.Member;
import accio.hogsmeade.store.member.model.repository.MemberRepository;
import accio.hogsmeade.store.member.model.service.MemberService;
import accio.hogsmeade.store.member.model.service.dto.AddMemberDto;
import accio.hogsmeade.store.member.model.service.dto.EditAddressDto;
import accio.hogsmeade.store.member.model.service.dto.EditLoginPwDto;
import accio.hogsmeade.store.member.model.validator.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

import static accio.hogsmeade.store.member.model.Grade.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;

    @Override
    public Long joinMember(AddMemberDto dto) {
        duplicateLoginId(dto);
        duplicateTel(dto);

        Member member = createMember(dto);
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    @Override
    public Long editLoginPw(String loginId, EditLoginPwDto dto) {
        Member member = memberValidator.findByLoginId(loginId);
        member.changeLoginPw(dto.getOldLoginPw(), dto.getNewLoginPw());
        return member.getId();
    }

    @Override
    public Long editTel(String loginId, String newTel) {
        Member member = memberValidator.findByLoginId(loginId);
        member.changeTel(newTel);
        return member.getId();
    }

    @Override
    public Long editAddress(String loginId, EditAddressDto dto) {
        Member member = memberValidator.findByLoginId(loginId);
        member.changeAddress(dto.getMainAddress(), dto.getDetailAddress());
        return member.getId();
    }

    @Override
    public Long withdrawal(String loginId, String loginPw) {
        Member member = memberValidator.findByLoginId(loginId);

        if (isNotEqualLoginPw(loginPw, member)) {
            throw new AuthorityException();
        }

        member.deActive();
        return member.getId();
    }

    private Member createMember(AddMemberDto dto) {
        Address address = Address.builder()
                .mainAddress(dto.getMainAddress())
                .detailAddress(dto.getDetailAddress())
                .build();
        return Member.builder()
                .loginId(dto.getLoginId())
                .loginPw(dto.getLoginPw())
                .name(dto.getName())
                .tel(dto.getTel())
                .address(address)
                .identity(dto.getIdentity())
                .grade(QUAFFLE)
                .roles(Collections.singletonList(dto.getAuthority().toString()))
                .build();
    }

    private void duplicateLoginId(AddMemberDto dto) {
        Optional<Member> loginId = memberRepository.findByLoginId(dto.getLoginId());
        if (loginId.isPresent()) {
            throw new DuplicateException();
        }
    }

    private void duplicateTel(AddMemberDto dto) {
        Optional<Member> tel = memberRepository.findByTel(dto.getTel());
        if (tel.isPresent()) {
            throw new DuplicateException();
        }
    }

    private boolean isNotEqualLoginPw(String loginPw, Member member) {
        return !member.getLoginPw().equals(loginPw);
    }
}
