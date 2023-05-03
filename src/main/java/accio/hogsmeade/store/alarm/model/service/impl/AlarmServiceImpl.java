package accio.hogsmeade.store.alarm.model.service.impl;

import accio.hogsmeade.store.alarm.model.Alarm;
import accio.hogsmeade.store.alarm.model.repository.AlarmRepository;
import accio.hogsmeade.store.alarm.model.service.AlarmService;
import accio.hogsmeade.store.common.exception.AuthorityException;
import accio.hogsmeade.store.member.model.Member;
import accio.hogsmeade.store.member.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

    private final AlarmRepository alarmRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long registerAlarm(Long memberId, String message) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException();
        }

        Member member = findMember.get();
        Alarm alarm = Alarm.builder()
                .content(message)
                .member(member)
                .build();
        Alarm savedAlarm = alarmRepository.save(alarm);
        return savedAlarm.getId();
    }

    @Override
    public Long openAlarm(String loginId, Long alarmId) {
        Member findMember = memberRepository.findByLoginId(loginId)
                .orElseThrow(NoSuchElementException::new);

        Alarm findAlarm = alarmRepository.findById(alarmId)
                .orElseThrow(NoSuchElementException::new);

        if (!findAlarm.getMember().getId().equals(findMember.getId())) {
            throw new AuthorityException();
        }

        findAlarm.alarmOpen();
        return findAlarm.getId();
    }

    @Override
    public int removeAlarms(String loginId, List<Long> alarmIds) {
        Member findMember = memberRepository.findByLoginId(loginId)
                .orElseThrow(NoSuchElementException::new);

        List<Alarm> findAlarms = alarmRepository.findAllByIds(alarmIds);
        for (Alarm alarm : findAlarms) {
            alarm.deActive();
        }
        return findAlarms.size();
    }
}
