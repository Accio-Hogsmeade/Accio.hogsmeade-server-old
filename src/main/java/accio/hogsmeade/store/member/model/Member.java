package accio.hogsmeade.store.member.model;

import accio.hogsmeade.store.common.model.Address;
import accio.hogsmeade.store.common.model.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column(unique = true, nullable = false, updatable = false, length = 20)
    private String loginId;
    @Column(nullable = false, length = 20)
    private String loginPw;
    @Column(nullable = false, updatable = false, length = 20)
    private String username;
    @Column(unique = true, nullable = false, length = 13)
    private String tel;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Identity identity;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Grade grade;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false, length = 20)
    private Authority authority;

    @Builder
    public Member(Long id, String loginId, String loginPw, String username, String tel, Address address, Identity identity, Grade grade, Authority authority) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.username = username;
        this.tel = tel;
        this.address = address;
        this.identity = identity;
        this.grade = grade;
        this.authority = authority;
    }
}