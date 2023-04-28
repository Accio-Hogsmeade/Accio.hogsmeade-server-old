package accio.hogsmeade.store.member.model;

import accio.hogsmeade.store.common.exception.EditException;
import accio.hogsmeade.store.common.model.Active;
import accio.hogsmeade.store.common.model.Address;
import accio.hogsmeade.store.common.model.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static accio.hogsmeade.store.common.model.Active.*;
import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Member extends TimeBaseEntity implements UserDetails {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column(unique = true, nullable = false, updatable = false, length = 20)
    private String loginId;
    @Column(nullable = false, length = 20)
    private String loginPw;
    @Column(nullable = false, updatable = false, length = 20)
    private String name;
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
    @Column(nullable = false, length = 20)
    private Active active;
    @ElementCollection(fetch = EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public Member() {
    }

    @Builder
    public Member(Long id, String loginId, String loginPw, String name, String tel, Address address, Identity identity, Grade grade, Active active, List<String> roles) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.identity = identity;
        this.grade = grade;
        this.active = ACTIVE;
        this.roles = roles;
    }

    //== 비즈니스 로직 ==//
    public void changeLoginPw(String oldLoginPW, String newLoginPW) {
        if (!this.loginPw.equals(oldLoginPW)) {
            throw new EditException();
        }
        this.loginPw = newLoginPW;
    }

    public void changeTel(String newTel) {
        this.tel = newTel;
    }

    public void changeAddress(String mainAddress, String detailAddress) {
        this.address = Address.builder()
                .mainAddress(mainAddress)
                .detailAddress(detailAddress)
                .build();
    }

    public void deActive() {
        this.active = DEACTIVE;
    }

    //== 스프링 시큐리티 ==//
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return loginPw;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}