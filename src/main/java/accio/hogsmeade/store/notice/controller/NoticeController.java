package accio.hogsmeade.store.notice.controller;

import accio.hogsmeade.store.jwt.SecurityUtil;
import accio.hogsmeade.store.notice.controller.dto.AddNoticeRequest;
import accio.hogsmeade.store.notice.model.service.NoticeService;
import accio.hogsmeade.store.notice.model.service.dto.AddNoticeDto;
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
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/register")
    public Long registerNotice(@Valid @RequestBody AddNoticeRequest request) {
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);
        AddNoticeDto dto = AddNoticeDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .pin(request.getPin())
                .build();
        return noticeService.registerNotice(loginId, dto);
    }
}
