package accio.hogsmeade.store.member.controller;

import accio.hogsmeade.store.jwt.TokenInfo;
import accio.hogsmeade.store.member.controller.dto.JoinMemberRequest;
import accio.hogsmeade.store.member.controller.dto.LoginRequest;
import accio.hogsmeade.store.member.model.Authority;
import accio.hogsmeade.store.member.model.Identity;
import accio.hogsmeade.store.member.model.service.MemberService;
import accio.hogsmeade.store.member.model.service.dto.AddMemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {

    public final MemberService memberService;

    @PostMapping("/join")
    public int join(@Valid @RequestBody JoinMemberRequest request) {
        if (request.getIdentity() == Identity.MUGGLE && request.getAuthority() != Authority.MEMBER) {
            return -1;
        }

        AddMemberDto addMemberDto = AddMemberDto.builder()
                .loginId(request.getLoginId())
                .loginPw(request.getLoginPw())
                .username(request.getUsername())
                .tel(request.getTel())
                .mainAddress(request.getMainAddress())
                .detailAddress(request.getDetailAddress())
                .identity(request.getIdentity())
                .authority(request.getAuthority())
                .build();

        Long memberId = memberService.joinMember(addMemberDto);
        log.debug("memberId = {}", memberId);
        return 1;
    }

    @PostMapping("/login")
    public TokenInfo login(@RequestBody LoginRequest request) {
        TokenInfo tokenInfo = memberService.login(request.getLoginId(), request.getLoginPw());
        return tokenInfo;
    }
}
