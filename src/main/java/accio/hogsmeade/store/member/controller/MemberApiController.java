package accio.hogsmeade.store.member.controller;

import accio.hogsmeade.store.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {

    public final MemberService memberService;

}
