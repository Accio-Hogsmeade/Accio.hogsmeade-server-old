package accio.hogsmeade.store.member.controller;

import accio.hogsmeade.store.jwt.TokenInfo;
import accio.hogsmeade.store.member.controller.dto.request.FindLoginIdRequest;
import accio.hogsmeade.store.member.controller.dto.request.FindLoginPwRequest;
import accio.hogsmeade.store.member.controller.dto.request.JoinMemberRequest;
import accio.hogsmeade.store.member.controller.dto.request.LoginRequest;
import accio.hogsmeade.store.member.model.Authority;
import accio.hogsmeade.store.member.model.Identity;
import accio.hogsmeade.store.member.model.service.AccountService;
import accio.hogsmeade.store.member.model.service.MemberService;
import accio.hogsmeade.store.member.model.service.dto.AddMemberDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(tags = {"회원"})
public class AccountApiController {

    private final MemberService memberService;
    private final AccountService accountService;

    @ApiOperation(value = "회원 가입")
    @PostMapping("/join")
    public int join(@Valid @RequestBody JoinMemberRequest request) {
        if (request.getIdentity() == Identity.MUGGLE && request.getAuthority() != Authority.MEMBER) {
            return -1;
        }

        AddMemberDto addMemberDto = AddMemberDto.builder()
                .loginId(request.getLoginId())
                .loginPw(request.getLoginPw())
                .name(request.getName())
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

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public TokenInfo login(@RequestBody LoginRequest request) {
        return accountService.login(request.getLoginId(), request.getLoginPw());
    }

    @ApiOperation(value = "로그인 아이디 찾기")
    @PostMapping("/forgot/loginId")
    public String findLoginId(@Valid @RequestBody FindLoginIdRequest request) {
        String loginId = accountService.findLoginId(request.getName(), request.getTel());
        return loginId;
    }

    @ApiOperation(value = "로그인 비밀번호 찾기")
    @PostMapping("/forgot/loginPw")
    public int findLoginPw(@Valid @RequestBody FindLoginPwRequest request) {
        int result = accountService.findLoginPw(request.getName(), request.getTel(), request.getLoginId());
        return result;
    }
}
