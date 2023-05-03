package accio.hogsmeade.store.alarm.model.repository.impl;

import accio.hogsmeade.store.alarm.controller.dto.AlarmResponse;
import accio.hogsmeade.store.alarm.model.repository.AlarmRepositoryCustom;
import accio.hogsmeade.store.alarm.model.repository.dto.AlarmSearchCondition;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static accio.hogsmeade.store.alarm.model.QAlarm.*;

public class AlarmRepositoryImpl implements AlarmRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AlarmRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<AlarmResponse> findBeforeThreeDays(Long memberId) {
        return queryFactory
                .select(Projections.fields(AlarmResponse.class,
                        alarm.id, alarm.title, alarm.open, alarm.createdDate))
                .from(alarm)
                .where(
                        alarm.member.id.eq(memberId),
                        alarm.createdDate.before(LocalDateTime.now().minusDays(3))
                )
                .orderBy(alarm.open.desc(), alarm.createdDate.desc())
                .fetch();
    }

    @Override
    public Page<AlarmResponse> findPagingByMemberId(Long memberId, AlarmSearchCondition condition, Pageable pageable) {
        List<AlarmResponse> alarms = queryFactory
                .select(Projections.fields(AlarmResponse.class,
                        alarm.id, alarm.title, alarm.open, alarm.createdDate))
                .from(alarm)
                .where(
                        alarm.member.id.eq(memberId),
                        isKeyword(condition.getKeyword()),
                        isNotOpen(condition.getOpen())

                )
                .orderBy(alarm.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = queryFactory
                .select(alarm.id)
                .from(alarm)
                .fetch()
                .size();

        return new PageImpl<>(alarms, pageable, totalCount);
    }

    private BooleanExpression isKeyword(String keyword) {
        return StringUtils.hasText(keyword) ? alarm.title.like(keyword) : null;
    }

    private BooleanExpression isNotOpen(Boolean open) {
        return open ? null : alarm.open.isFalse();
    }

}
