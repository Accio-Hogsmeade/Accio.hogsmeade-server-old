package accio.hogsmeade.store.member.model.repository;

import accio.hogsmeade.store.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(@Param("loginId") String loginId);

    Optional<Member> findByTel(@Param("tel") String tel);
}
