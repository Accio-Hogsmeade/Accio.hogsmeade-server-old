package accio.hogsmeade.store.store.model;

import accio.hogsmeade.store.common.model.TimeBaseEntity;
import accio.hogsmeade.store.common.model.UploadFile;
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
public class Store extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "store_id")
    private Long id;
    @Column(unique = true, nullable = false, length = 100)
    private String storeName;
    @Lob
    @Column(nullable = false)
    private String content;
    @Embedded
    private UploadFile uploadFile;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Store(Long id, String storeName, String content, UploadFile uploadFile, Member member) {
        this.id = id;
        this.storeName = storeName;
        this.content = content;
        this.uploadFile = uploadFile;
        this.member = member;
    }
}
