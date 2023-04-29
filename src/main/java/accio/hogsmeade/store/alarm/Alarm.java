package accio.hogsmeade.store.alarm;

import accio.hogsmeade.store.common.model.TimeBaseEntity;
import accio.hogsmeade.store.member.model.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Alarm extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "alarm_id")
    private Long id;
    @Column(nullable = false, updatable = false, length = 50)
    private String content;
    @Column(nullable = false)
    private Boolean open;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Alarm(Long id, String content, Boolean open) {
        this.id = id;
        this.content = content;
        this.open = open;
    }
}
