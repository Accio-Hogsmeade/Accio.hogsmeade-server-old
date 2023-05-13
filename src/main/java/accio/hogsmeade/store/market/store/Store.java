package accio.hogsmeade.store.market.store;

import accio.hogsmeade.store.common.model.Active;
import accio.hogsmeade.store.common.model.TimeBaseEntity;
import accio.hogsmeade.store.common.model.UploadFile;
import accio.hogsmeade.store.client.member.Member;
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
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Active active;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Store(Long id, String storeName, String content, UploadFile uploadFile, Active active, Member member) {
        this.id = id;
        this.storeName = storeName;
        this.content = content;
        this.uploadFile = uploadFile;
        this.active = active;
        this.member = member;
    }

    //== 비즈니스 로직 ==//
    public void changeStore(String content, UploadFile uploadFile) {
        this.content = content;
        this.uploadFile = uploadFile;
    }
}
