package accio.hogsmeade.store.member.model.repository;

import accio.hogsmeade.store.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
