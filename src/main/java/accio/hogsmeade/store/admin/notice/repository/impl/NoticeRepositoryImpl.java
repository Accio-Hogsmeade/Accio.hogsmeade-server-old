package accio.hogsmeade.store.admin.notice.repository.impl;

import accio.hogsmeade.store.admin.controller.response.notice.DetailNoticeResponse;
import accio.hogsmeade.store.admin.controller.response.notice.NoticeResponse;
import accio.hogsmeade.store.admin.notice.repository.NoticeRepositoryCustom;
import accio.hogsmeade.store.admin.notice.repository.dto.NoticeSearchCondition;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static accio.hogsmeade.store.admin.notice.QNotice.*;

public class NoticeRepositoryImpl implements NoticeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public NoticeRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<DetailNoticeResponse> findDetailById(Long noticeId) {
        DetailNoticeResponse content = queryFactory
                .select(Projections.fields(DetailNoticeResponse.class,
                        notice.id, notice.title, notice.content, notice.createdDate))
                .from(notice)
                .where(notice.id.eq(noticeId))
                .fetchOne();
        return Optional.ofNullable(content);
    }

    @Override
    public Page<NoticeResponse> findByCondition(NoticeSearchCondition condition, Pageable pageable) {
        List<NoticeResponse> notices = queryFactory
                .select(Projections.fields(NoticeResponse.class, notice.id, notice.title, notice.createdDate))
                .from(notice)
                .where(
                        notice.pin.eq("0"),
                        isKeyword(condition.getKeyword())
                )
                .orderBy(notice.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = queryFactory
                .select(notice.id)
                .from(notice)
                .where()
                .fetch()
                .size();
        return new PageImpl<>(notices, pageable, totalCount);
    }

    @Override
    public List<NoticeResponse> findByPin() {
        return queryFactory
                .select(Projections.fields(NoticeResponse.class, notice.id, notice.title, notice.createdDate))
                .from(notice)
                .where(notice.pin.eq("1"))
                .orderBy(notice.createdDate.desc())
                .fetch();
    }

    private BooleanExpression isKeyword(String keyword) {
        return StringUtils.hasText(keyword) ? notice.title.like(keyword)
                .or(notice.content.like(keyword)) : null;
    }
}
