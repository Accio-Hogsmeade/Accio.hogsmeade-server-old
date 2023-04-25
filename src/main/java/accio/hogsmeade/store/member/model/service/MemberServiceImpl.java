package accio.hogsmeade.store.member.model.service;

import accio.hogsmeade.store.common.exception.DuplicateException;
import accio.hogsmeade.store.common.model.Address;
import accio.hogsmeade.store.member.model.Grade;
import accio.hogsmeade.store.member.model.Member;
import accio.hogsmeade.store.member.model.repository.MemberRepository;
import accio.hogsmeade.store.member.model.service.dto.AddMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static accio.hogsmeade.store.member.model.Grade.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

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
                .username(dto.getUsername())
                .tel(dto.getTel())
                .address(address)
                .identity(dto.getIdentity())
                .grade(QUAFFLE)
                .authority(dto.getAuthority())
                .build();

        Member savedMember = memberRepository.save(member);

        return savedMember.getId();
    }
}
