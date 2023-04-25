package accio.hogsmeade.store.member.model;

import accio.hogsmeade.store.common.model.Address;
import accio.hogsmeade.store.common.model.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
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
    @Builder.Default
    @ElementCollection(fetch = EAGER)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false, length = 20)
    private List<Authority> authorities;

    @Builder
    public Member(Long id, String loginId, String loginPw, String name, String tel, Address address, Identity identity, Grade grade, List<Authority> authorities) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.identity = identity;
        this.grade = grade;
        this.authorities = authorities;
    }

    //== 스프링 시큐리티 ==//
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.toString()))
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