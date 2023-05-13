package accio.hogsmeade.store.client.board.service;

import accio.hogsmeade.store.client.board.Board;
import accio.hogsmeade.store.client.board.Category;
import accio.hogsmeade.store.client.board.repository.BoardRepository;
import accio.hogsmeade.store.client.board.repository.CategoryRepository;
import accio.hogsmeade.store.client.board.service.dto.AddBoardDto;
import accio.hogsmeade.store.client.board.service.dto.EditBoardDto;
import accio.hogsmeade.store.common.model.Address;
import accio.hogsmeade.store.client.member.Member;
import accio.hogsmeade.store.client.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

import static accio.hogsmeade.store.client.member.Grade.QUAFFLE;
import static accio.hogsmeade.store.client.member.Identity.STUDENT;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    CategoryRepository categoryRepository;

    private Member savedMember;
    private Category savedCategory;

    @BeforeEach
    void beforeEach() {
        Address address = Address.builder().mainAddress("mainAddress").detailAddress("detailAddress").build();
        Member member = Member.builder()
                .loginId("harry")
                .loginPw("abcd1234!")
                .name("harrypotter")
                .tel("077-1234-1234")
                .address(address)
                .identity(STUDENT)
                .grade(QUAFFLE)
                .roles(Collections.singletonList("MEMBER"))
                .build();
        savedMember = memberRepository.save(member);
        Category category = Category.builder()
                .name("자유")
                .build();
        savedCategory = categoryRepository.save(category);
    }

    @Test
    @DisplayName("게시판 글 작성")
    void writeBoard() {
        //given
        AddBoardDto addBoardDto = getAddBoardDto();

        //when
        Long boardId = boardService.writeBoard(savedMember.getLoginId(), savedCategory.getId(), addBoardDto);

        ///then
        Optional<Board> findBoard = boardRepository.findById(boardId);
        assertThat(findBoard).isPresent();
    }

    @Test
    @DisplayName("게시판 글 작성 - 로그인 유저 없음 에러")
    void writeBoardMemberEmpty() {
        //given
        AddBoardDto addBoardDto = getAddBoardDto();

        //when

        ///then
        assertThatThrownBy(() -> boardService.writeBoard("testUserId", savedCategory.getId(), addBoardDto))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("게시판 글 작성 - 카테고리 에러")
    void writeBoardCategoryError() {
        //given
        AddBoardDto addBoardDto = getAddBoardDto();

        //when

        ///then
        assertThatThrownBy(() -> boardService.writeBoard(savedMember.getLoginId(), -1L, addBoardDto))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("게시판 글 수정")
    void editBoard() {
        //given
        AddBoardDto addBoardDto = getAddBoardDto();
        Board savedBoard = boardRepository.save(Board.builder()
                .title(addBoardDto.getTitle())
                .content(addBoardDto.getContent())
                .category(savedCategory)
                .member(savedMember)
                .build());
        EditBoardDto editBoardDto = EditBoardDto.builder()
                .categoryId(savedCategory.getId())
                .uploadFileName(null)
                .title("123")
                .content("456")
                .build();

        //when
        Long editBoard = boardService.editBoard(savedMember.getLoginId(), savedBoard.getId(), editBoardDto);
        assertThat(editBoard).isEqualTo(savedBoard.getId());
    }

    private AddBoardDto getAddBoardDto() {
        return AddBoardDto.builder()
                .title("제목")
                .content("내용")
//                .uploadFile(null)
                .build();
    }
}