package accio.hogsmeade.store.notice.model.repository;

import accio.hogsmeade.store.notice.model.Notice;
import accio.hogsmeade.store.notice.model.QNotice;
import accio.hogsmeade.store.notice.model.repository.dto.NoticeSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

import java.util.List;

import static accio.hogsmeade.store.notice.model.QNotice.*;

public class NoticeRepositoryImpl implements NoticeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public NoticeRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Notice> findByCondition(NoticeSearchCondition condition, Pageable pageable) {
        List<Notice> notices = queryFactory.select(notice)
                .from(notice)
                .where(isKeyword(condition.getKeyword()))
                .orderBy(notice.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = queryFactory
                .selectFrom(notice)
                .where()
                .fetch()
                .size();
        return new PageImpl<>(notices, pageable, totalCount);
    }

    private BooleanExpression isKeyword(String keyword) {
        return StringUtils.hasText(keyword) ? notice.title.like(keyword)
                .or(notice.content.like(keyword)) : null;
    }
}
