package accio.hogsmeade.store.board.model.repository.impl;

import accio.hogsmeade.store.board.controller.dto.response.BoardResponse;
import accio.hogsmeade.store.board.model.QBoard;
import accio.hogsmeade.store.board.model.QCategory;
import accio.hogsmeade.store.board.model.repository.BoardRepositoryCustom;
import accio.hogsmeade.store.board.model.repository.dto.BoardSearchCondition;
import accio.hogsmeade.store.member.model.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import static accio.hogsmeade.store.board.model.QBoard.*;
import static accio.hogsmeade.store.board.model.QCategory.category;
import static accio.hogsmeade.store.member.model.QMember.member;

public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<BoardResponse> searchByCondition(BoardSearchCondition condition, Pageable pageable) {
        queryFactory
                .select(board)
                .from(board)
                .join(member).fetchJoin()
                .join(category).fetchJoin()
                .where(board.title.like(condition.getKeyword()))
        return null;
    }
}
