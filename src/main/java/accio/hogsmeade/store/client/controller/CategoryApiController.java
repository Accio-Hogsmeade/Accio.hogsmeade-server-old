package accio.hogsmeade.store.client.controller;

import accio.hogsmeade.store.client.board.service.CategoryService;
import accio.hogsmeade.store.client.board.service.dto.AddBoardDto;
import accio.hogsmeade.store.client.controller.request.board.AddBoardRequest;
import accio.hogsmeade.store.jwt.SecurityUtil;
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
@RequestMapping("/category")
@Api(tags = {"카테고리"})
public class CategoryApiController {

    private final CategoryService categoryService;

    @ApiOperation(value = "카테고리 등록")
    @PostMapping("/add")
    public Long add(@RequestBody String categoryName) {
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);
        return categoryService.addCategory(loginId,categoryName);
    }

}
