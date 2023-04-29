package accio.hogsmeade.store.alarm.model.service.impl;

import accio.hogsmeade.store.alarm.model.Alarm;
import accio.hogsmeade.store.alarm.model.repository.AlarmRepository;
import accio.hogsmeade.store.alarm.model.service.AlarmService;
import accio.hogsmeade.store.member.model.Member;
import accio.hogsmeade.store.member.model.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
