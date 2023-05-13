package accio.hogsmeade.store.admin.controller;

import accio.hogsmeade.store.admin.notice.service.NoticeQueryService;
import accio.hogsmeade.store.jwt.SecurityUtil;
import accio.hogsmeade.store.admin.controller.request.notice.AddNoticeRequest;
import accio.hogsmeade.store.admin.controller.response.notice.DetailNoticeResponse;
import accio.hogsmeade.store.admin.controller.response.notice.NoticeResponse;
import accio.hogsmeade.store.admin.notice.repository.dto.NoticeSearchCondition;
import accio.hogsmeade.store.admin.notice.service.NoticeService;
import accio.hogsmeade.store.admin.notice.service.dto.AddNoticeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;
    private final NoticeQueryService noticeQueryService;

    @GetMapping
    public Page<NoticeResponse> searchNotices(
            @RequestParam String keyword,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        log.debug("searchNotice");
        NoticeSearchCondition condition = NoticeSearchCondition
                .builder().keyword(keyword).build();
        PageRequest pageRequest = PageRequest.of(page, size);
        return noticeQueryService.searchByCondition(condition, pageRequest);
    }

    @GetMapping("/{noticeId}")
    public DetailNoticeResponse searchNotice(@PathVariable Long noticeId) {
        return noticeQueryService.searchDetail(noticeId);
    }


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
