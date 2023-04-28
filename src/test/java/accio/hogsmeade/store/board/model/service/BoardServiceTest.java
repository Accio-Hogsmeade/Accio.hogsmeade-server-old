package accio.hogsmeade.store.board.model.service;

import accio.hogsmeade.store.board.model.Board;
import accio.hogsmeade.store.board.model.Category;
import accio.hogsmeade.store.board.model.repository.BoardRepository;
import accio.hogsmeade.store.board.model.repository.CategoryRepository;
import accio.hogsmeade.store.board.model.service.dto.AddBoardDto;
import accio.hogsmeade.store.common.model.Address;
import accio.hogsmeade.store.member.model.Member;
import accio.hogsmeade.store.member.model.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

import static accio.hogsmeade.store.member.model.Grade.QUAFFLE;
import static accio.hogsmeade.store.member.model.Identity.STUDENT;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

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
        AddBoardDto addBoardDto = AddBoardDto.builder()
                .title("제목")
                .content("내용")
                .uploadFile(null)
                .build();
        //when
        Long boardId = boardService.writeBoard(savedMember.getLoginId(), savedCategory.getId(), addBoardDto);

        ///then
        Optional<Board> findBoard = boardRepository.findById(boardId);
//        assertThat(findBoard.get().getId()).isEqualTo(boardId);
        assertThat(findBoard).isPresent();
    }
}