package accio.hogsmeade.store.board.model.service.impl;

import accio.hogsmeade.store.board.model.Board;
import accio.hogsmeade.store.board.model.Category;
import accio.hogsmeade.store.board.model.repository.BoardRepository;
import accio.hogsmeade.store.board.model.repository.CategoryRepository;
import accio.hogsmeade.store.board.model.service.BoardService;
import accio.hogsmeade.store.board.model.service.dto.AddBoardDto;
import accio.hogsmeade.store.member.model.Member;
import accio.hogsmeade.store.member.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Long writeBoard(String loginId, Long categoryId, AddBoardDto addBoardDto) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException();
        }
        Optional<Category> findCategory = categoryRepository.findById(categoryId);
        if (findCategory.isEmpty()) {
            throw new NoSuchElementException();
        }

        Member member = findMember.get();
        Category category = findCategory.get();
        Board board = Board.builder()
                .title(addBoardDto.getTitle())
                .content(addBoardDto.getContent())
                .uploadFile(addBoardDto.getUploadFile())
                .member(member)
                .category(category)
                .build();
        Board savedBoard = boardRepository.save(board);
        return savedBoard.getId();
    }
}
