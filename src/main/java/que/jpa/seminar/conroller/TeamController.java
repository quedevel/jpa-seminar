package que.jpa.seminar.conroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import que.jpa.seminar.repository.TeamRepository;

/**
 * packageName    : que.jpa.seminar.conroller
 * fileName       : TeamController
 * author         : quedevel
 * date           : 2023-08-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-08-08        quedevel       최초 생성
 */
@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {

  private final TeamRepository teamRepository;

  @DeleteMapping("/{id}")
  public String delete(@PathVariable Long id) {
    try {
      teamRepository.deleteById(id);
      return "ok";
    } catch (Exception e) {
      return "Nope...!";
    }
  }

}
