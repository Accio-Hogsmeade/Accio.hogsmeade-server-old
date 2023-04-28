package accio.hogsmeade.store.board.model;

import accio.hogsmeade.store.common.model.Active;
import accio.hogsmeade.store.common.model.TimeBaseEntity;
import accio.hogsmeade.store.common.model.UploadFile;
import accio.hogsmeade.store.member.model.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.*;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Board extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;
    @Column(nullable = false, length = 50)
    private String title;
    @Lob
    private String content;
    private int hit;
    @Embedded
    private UploadFile uploadFile;
    @Enumerated(STRING)
    private BoardStatus status;
    @Enumerated(STRING)
    private Active active;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public Board(Long id, String title, String content, int hit, UploadFile uploadFile, BoardStatus status, Active active, Member member, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.uploadFile = uploadFile;
        this.status = status;
        this.active = active;
        this.member = member;
        this.category = category;
    }
}