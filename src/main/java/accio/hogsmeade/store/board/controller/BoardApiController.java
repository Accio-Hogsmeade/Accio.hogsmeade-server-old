package accio.hogsmeade.store.board.controller;

import accio.hogsmeade.store.board.controller.dto.AddBoardRequest;
import accio.hogsmeade.store.board.controller.dto.EditBoardRequest;
import accio.hogsmeade.store.board.controller.dto.response.BoardResponse;
import accio.hogsmeade.store.board.model.repository.dto.BoardSearchCondition;
import accio.hogsmeade.store.board.model.service.BoardService;
import accio.hogsmeade.store.board.model.service.dto.AddBoardDto;
import accio.hogsmeade.store.board.model.service.dto.EditBoardDto;
import accio.hogsmeade.store.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/boards")
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

    @PostMapping("/{boardId}/edit")
    public Long edit(@PathVariable Long boardId, @Valid @RequestBody EditBoardRequest request) {
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId = {}", loginId);
        EditBoardDto editBoardDto = EditBoardDto.builder()
                .categoryId(request.getCategoryId())
                .title(request.getTitle())
                .content(request.getContent())
                .uploadFileName(request.getUploadFileName())
                .build();
        return boardService.editBoard(loginId, boardId, editBoardDto);
    }

    //search?a=xxx&b=xxx
    @GetMapping
    public Page<BoardResponse> list(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "") String category,
            @RequestParam(defaultValue = "1") Integer page
            ){
        BoardSearchCondition condition = BoardSearchCondition.builder()
                .keyword(keyword)
                .category(category)
                .build();
        PageRequest pageRequest = PageRequest.of(page, 20);
        return boardService.getBoardList(condition, pageRequest);
    }
}
