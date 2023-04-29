package accio.hogsmeade.store.board.controller;

import accio.hogsmeade.store.board.controller.dto.AddBoardRequest;
import accio.hogsmeade.store.board.model.service.BoardService;
import accio.hogsmeade.store.board.model.service.dto.AddBoardDto;
import accio.hogsmeade.store.jwt.SecurityUtil;
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
@RequestMapping("/board")
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/write")
    public Long write(@Valid @RequestBody AddBoardRequest request) {
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);
        AddBoardDto addBoardDto = AddBoardDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .uploadFileName(request.getUploadFileName())
                .build();
        return boardService.writeBoard(loginId, request.getCategoryId(), addBoardDto);
    }
}
