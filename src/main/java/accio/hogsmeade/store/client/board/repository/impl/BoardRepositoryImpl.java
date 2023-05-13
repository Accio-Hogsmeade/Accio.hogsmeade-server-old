package accio.hogsmeade.store.client.board.repository.impl;

import accio.hogsmeade.store.client.controller.response.board.BoardDetailResponse;
import accio.hogsmeade.store.client.controller.response.board.BoardResponse;
import accio.hogsmeade.store.client.board.repository.BoardRepositoryCustom;
import accio.hogsmeade.store.client.board.repository.dto.BoardSearchCondition;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

import java.util.List;

import static accio.hogsmeade.store.client.board.QBoard.*;
import static accio.hogsmeade.store.client.board.QCategory.*;
import static accio.hogsmeade.store.client.member.QMember.*;

public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<BoardResponse> searchByCondition(BoardSearchCondition condition, Pageable pageable) {
        List<BoardResponse> boardList = queryFactory
                .select(Projections.fields(BoardResponse.class,
                        board.id,
                        board.category.name,
                        board.title,
                        board.hit,
                        member.identity,
                        board.createdDate,
                        board.uploadFile))
                .from(board)
                .join(board.member, member)
                .join(board.category, category)
                .where(isKeyword(condition.getKeyword()))
                .orderBy(board.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long count = queryFactory
                .select(board.id)
                .from(board)
                .fetch()
                .size();
        return new PageImpl<>(boardList, pageable, count);
    }

    @Override
    public BoardDetailResponse searchById(Long boardId) {
        BoardDetailResponse boardDetailResponse = queryFactory
                .select(Projections.fields(BoardDetailResponse.class,
                        board.id.as("boardId"),
                        board.category.name.as("category"),
                        board.title,
                        board.content,
                        board.hit,
                        member.identity,
                        board.createdDate,
                        board.uploadFile,
                        member.id.as("memberId")))
                .from(board)
                .join(member,board.member)
                .join(board.category, category)
                .where(board.id.eq(boardId))
                .fetchOne();
        return boardDetailResponse;
    }

    private BooleanExpression isKeyword(String keyword) {
        return StringUtils.hasText(keyword) ? board.title.like(keyword) : null;
    }
}
