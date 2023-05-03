package accio.hogsmeade.store.alarm.model;

import accio.hogsmeade.store.common.model.Active;
import accio.hogsmeade.store.common.model.TimeBaseEntity;
import accio.hogsmeade.store.member.model.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static accio.hogsmeade.store.common.model.Active.DEACTIVE;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Alarm extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "alarm_id")
    private Long id;
    @Column(nullable = false, updatable = false, length = 20)
    private String title;
    @Column(nullable = false, updatable = false, length = 50)
    private String content;
    @Column(nullable = false)
    private Boolean open;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Active active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Alarm(Long id, String title, String content, Boolean open, Active active, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.open = open;
        this.active = active;
        this.member = member;
    }

    //== 비즈니스 로직 ==//
    public void alarmOpen() {
        this.open = true;
    }

    public void deActive() {
        this.active = DEACTIVE;
    }
}
