package que.jpa.seminar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import que.jpa.seminar.entity.Team;

/**
 * packageName    : que.jpa.seminar.repository
 * fileName       : TeamRepository
 * author         : quedevel
 * date           : 2023-08-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-08-08        quedevel       최초 생성
 */
public interface TeamRepository extends JpaRepository<Team, Long> {

}
