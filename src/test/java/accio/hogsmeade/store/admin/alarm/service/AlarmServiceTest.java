package accio.hogsmeade.store.admin.alarm.service;

import accio.hogsmeade.store.admin.alarm.Alarm;
import accio.hogsmeade.store.admin.alarm.repository.AlarmRepository;
import accio.hogsmeade.store.common.model.Address;
import accio.hogsmeade.store.client.member.Member;
import accio.hogsmeade.store.client.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

import static accio.hogsmeade.store.common.model.Active.*;
import static accio.hogsmeade.store.client.member.Grade.QUAFFLE;
import static accio.hogsmeade.store.client.member.Identity.WIZARD;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class AlarmServiceTest {

    @Autowired
    private AlarmService alarmService;
    @Autowired
    private AlarmRepository alarmRepository;
    @Autowired
    private MemberRepository memberRepository;

    private Member savedMember;

    @BeforeEach
    void beforeEach() {
        createMember();
    }

    @Test
    @DisplayName("알림 등록")
    void registerAlarm() {
        //given
        String message = "페이 머니가 충전되었습니다.";

        //when
        Long alarmId = alarmService.registerAlarm(savedMember.getId(), message);

        //then
        Optional<Alarm> findAlarm = alarmRepository.findById(alarmId);
        assertThat(findAlarm).isPresent();
    }

    @Test
    @DisplayName("알림 열람")
    void openAlarm() {
        //given
        Alarm alarm = Alarm.builder()
                .title("페이 머니 충전")
                .content("페이 머니가 충전되었습니다.")
                .open(false)
                .active(ACTIVE)
                .member(savedMember)
                .build();
        Alarm savedAlarm = alarmRepository.save(alarm);

        //when
        Long alarmId = alarmService.openAlarm(savedMember.getLoginId(), savedAlarm.getId());

        //then
        Alarm findAlarm = alarmRepository.findById(alarmId).get();
        assertThat(findAlarm.getOpen()).isTrue();
    }

    @Test
    @DisplayName("알림 열람#미존재 알림")
    void openAlarmByAlarm() {
        //given

        //when
        //then
        assertThatThrownBy(() -> alarmService.openAlarm(savedMember.getLoginId(), 0L))
                .isInstanceOf(NoSuchElementException.class);
    }
    
    @Test
    @DisplayName("알림 삭제")
    void removeAlarms() {
        //given
        Alarm alarm = Alarm.builder()
                .title("페이 머니 충전")
                .content("페이 머니가 충전되었습니다.")
                .open(false)
                .active(ACTIVE)
                .member(savedMember)
                .build();
        Alarm savedAlarm = alarmRepository.save(alarm);

        //when
        int count = alarmService.removeAlarms(savedMember.getLoginId(), Collections.singletonList(savedAlarm.getId()));

        //then
        Alarm findAlarm = alarmRepository.findById(savedAlarm.getId()).get();
        assertThat(findAlarm.getActive()).isEqualTo(DEACTIVE);
    }
    
    private void createMember() {
        Address address = Address.builder().mainAddress("mainAddress").detailAddress("detailAddress").build();
        Member member = Member.builder()
                .loginId("harry")
                .loginPw("abcd1234!")
                .name("harrypotter")
                .tel("077-1234-1234")
                .address(address)
                .identity(WIZARD)
                .grade(QUAFFLE)
                .roles(Collections.singletonList("ADMIN"))
                .build();
        savedMember = memberRepository.save(member);
    }
}