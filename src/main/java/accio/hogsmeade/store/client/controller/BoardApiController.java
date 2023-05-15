package accio.hogsmeade.store.client.controller;

import accio.hogsmeade.store.client.controller.request.board.AddBoardRequest;
import accio.hogsmeade.store.client.controller.request.board.EditBoardRequest;
import accio.hogsmeade.store.client.controller.response.board.BoardDetailResponse;
import accio.hogsmeade.store.client.controller.response.board.BoardResponse;
import accio.hogsmeade.store.client.board.repository.dto.BoardSearchCondition;
import accio.hogsmeade.store.client.board.service.BoardService;
import accio.hogsmeade.store.client.board.service.dto.AddBoardDto;
import accio.hogsmeade.store.client.board.service.dto.EditBoardDto;
import accio.hogsmeade.store.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/boards")
@Api(tags = {"게시판"})
public class BoardApiController {

    private final BoardService boardService;

    @ApiOperation(value = "게시판 등록")
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

    @GetMapping
    public Result<?> list(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "") String category,
            @RequestParam(defaultValue = "1") Integer page
            ){
        BoardSearchCondition condition = BoardSearchCondition.builder()
                .keyword(keyword)
                .category(category)
                .build();
        PageRequest pageRequest = PageRequest.of(page, 20);
        Page<BoardResponse> boardList = boardService.getBoardList(condition, pageRequest);
        return new Result<Page<BoardResponse>>(boardList);
    }

    @GetMapping("/{boardId}")
    public Result<?> detail(@PathVariable Long boardId){
        BoardDetailResponse board = boardService.getBoard(boardId);
        return new Result<BoardDetailResponse>(board);
    }

    @PostMapping("/{boardId}")
    public Long delete(@PathVariable Long boardId) {
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId = {}", loginId);
        return boardService.deactiveBoard(loginId, boardId);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
}
