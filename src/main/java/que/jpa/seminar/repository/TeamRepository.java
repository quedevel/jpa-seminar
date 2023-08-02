package que.jpa.seminar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import que.jpa.seminar.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
