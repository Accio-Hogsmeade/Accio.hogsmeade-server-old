package accio.hogsmeade.store.alarm.model.repository.impl;

import accio.hogsmeade.store.alarm.controller.dto.PreviewAlarmResponse;
import accio.hogsmeade.store.alarm.model.repository.AlarmRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

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
    public List<PreviewAlarmResponse> findBeforeThreeDays(Long memberId) {
        return queryFactory
                .select(Projections.fields(PreviewAlarmResponse.class,
                        alarm.id, alarm.title, alarm.open, alarm.createdDate))
                .from(alarm)
                .where(
                        alarm.member.id.eq(memberId),
                        alarm.createdDate.before(LocalDateTime.now().minusDays(3))
                )
                .orderBy(alarm.open.desc(), alarm.createdDate.desc())
                .fetch();
    }
}
