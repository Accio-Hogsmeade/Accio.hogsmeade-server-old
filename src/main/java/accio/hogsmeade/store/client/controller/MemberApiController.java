package accio.hogsmeade.store.client.controller;

import accio.hogsmeade.store.client.controller.request.member.EditLoginPwRequest;
import accio.hogsmeade.store.client.controller.request.member.EditTelRequest;
import accio.hogsmeade.store.client.member.service.dto.EditAddressDto;
import accio.hogsmeade.store.client.member.service.dto.EditLoginPwDto;
import accio.hogsmeade.store.jwt.SecurityUtil;
import accio.hogsmeade.store.client.controller.request.member.EditAddressRequest;
import accio.hogsmeade.store.client.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@Api(tags = {"회원"})
@RequestMapping("/member")
public class MemberApiController {

    public final MemberService memberService;

    @ApiOperation(value = "회원 비밀번호 변경")
    @PostMapping("/edit/loginPw")
    public void editLoginPw(@Valid @RequestBody EditLoginPwRequest request) {
        String loginId = SecurityUtil.getCurrentLoginId();
        EditLoginPwDto dto = EditLoginPwDto.builder()
                .newLoginPw(request.getNewLoginPw())
                .oldLoginPw(request.getOldLoginPw())
                .build();

        Long memberId = memberService.editLoginPw(loginId, dto);
        log.debug("edit loginPw = {}", memberId);
    }

    @ApiOperation(value = "회원 연락처 변경")
    @PostMapping("/edit/tel")
    public void editTel(@Valid @RequestBody EditTelRequest request) {
        String loginId = SecurityUtil.getCurrentLoginId();
        Long memberId = memberService.editTel(loginId, request.getTel());
        log.debug("edit tel = {}", memberId);
    }

    @ApiOperation(value = "회원 주소 변경")
    @PostMapping("/edit/address")
    public void editAddress(@Valid @RequestBody EditAddressRequest request) {
        String loginId = SecurityUtil.getCurrentLoginId();
        EditAddressDto dto = EditAddressDto.builder()
                .mainAddress(request.getMainAddress())
                .detailAddress(request.getDetailAddress())
                .build();

        Long memberId = memberService.editAddress(loginId, dto);
        log.debug("edit address = {}", memberId);
    }
}
