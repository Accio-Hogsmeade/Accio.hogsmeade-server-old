package accio.hogsmeade.store.notice.model;

import accio.hogsmeade.store.common.model.TimeBaseEntity;
import accio.hogsmeade.store.member.model.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Notice extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "notice_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;
    @Lob
    @Column(nullable = false)
    private String content;
    @Column(nullable = false, length = 1)
    private String pin;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Notice(Long id, String title, String content, String pin, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.pin = pin;
        this.member = member;
    }

    //== 비즈니스 로직 ==//
    public void changeNotice(String title, String content, String pin) {
        this.title = title;
        this.content = content;
        this.pin = pin;
    }
}
