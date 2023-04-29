package accio.hogsmeade.store.alarm.model.service;

import accio.hogsmeade.store.alarm.model.Alarm;
import accio.hogsmeade.store.alarm.model.repository.AlarmRepository;
import accio.hogsmeade.store.common.model.Address;
import accio.hogsmeade.store.member.model.Member;
import accio.hogsmeade.store.member.model.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

import static accio.hogsmeade.store.member.model.Grade.QUAFFLE;
import static accio.hogsmeade.store.member.model.Identity.WIZARD;
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
    @DisplayName("알림 등록#미가입 회원")
    void registerAlarmByMember() {
        //given
        String message = "페이 머니가 충전되었습니다.";

        //when
        //then
        assertThatThrownBy(() -> alarmService.registerAlarm(0L, message))
                .isInstanceOf(NoSuchElementException.class);
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