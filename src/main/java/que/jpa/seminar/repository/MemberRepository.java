package que.jpa.seminar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import que.jpa.seminar.entity.Member;

/**
 * packageName    : que.jpa.seminar.repository
 * fileName       : MemberRepository
 * author         : quedevel
 * date           : 2023-08-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-08-08        quedevel       최초 생성
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

}
