package que.jpa.seminar.conroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import que.jpa.seminar.entity.Member;
import que.jpa.seminar.repository.MemberRepository;

/**
 * packageName    : que.jpa.seminar.conroller
 * fileName       : MemberController
 * author         : quedevel
 * date           : 2023-08-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-08-08        quedevel       최초 생성
 */
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

  private final MemberRepository memberRepository;

  @GetMapping("/{id}")
  public Member select(@PathVariable Long id){
    return memberRepository.findById(id).orElse(null);
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable Long id){
    try {
      memberRepository.deleteById(id);
      return "ok";

    } catch (Exception e) {
      return "Nope...!";
    }
  }

}
