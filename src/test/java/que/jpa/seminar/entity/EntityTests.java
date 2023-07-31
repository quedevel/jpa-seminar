package que.jpa.seminar.entity;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class EntityTests {

  @PersistenceContext
  EntityManager em;

  @Test
  @DisplayName("[다대일 N:1] 연관관계의 주인에서 저장후 조회시 저장 성공")
  void manyToOneTest1(){
    //given
    Team team = new Team();
    team.setName("teamA");
    em.persist(team);

    Member member = new Member();
    member.setUsername("memberA");
    member.setTeam(team);
    em.persist(member);

    em.flush();
    em.clear();

    //when
    Member findMember = em.find(Member.class, member.getId());

    //then
    assertThat(findMember.getTeam().getId()).isEqualTo(team.getId());
  }

  @Test
  @DisplayName("[다대일 N:1] 연관관계의 주인이 아닌곳에서 저장후 조회시 저장 실패")
  void manyToOneTest2(){
    //given
    Member member = new Member();
    member.setUsername("memberA");
    em.persist(member);

    Team team = new Team();
    team.setName("teamA");
    team.getMembers().add(member);
    em.persist(team);

    em.flush();
    em.clear();

    //when
    Member findMember = em.find(Member.class, member.getId());

    //then
    assertThat(findMember.getTeam()).isNull();
  }

  @Test
  @DisplayName("[일대다 1:N] 연관관계 주인을 persist 동작시 update 동작")
  void oneToManyTest1(){
    //given
    Admin admin1 = new Admin();
    admin1.setName("admin1");
    Admin admin2 = new Admin();
    admin2.setName("admin2");

    Team team = new Team();
    team.setName("team");
    team.getAdmins().add(admin1);
    team.getAdmins().add(admin2);

    em.persist(admin1);
    em.persist(admin2);
    em.persist(team);

    em.flush();
    em.clear();

    //when
    Admin findAdmin1 = em.find(Admin.class, admin1.getId());
    Admin findAdmin2 = em.find(Admin.class, admin2.getId());

    //then
    assertThat(findAdmin1.getTeam().getId()).isEqualTo(team.getId());
    assertThat(findAdmin2.getTeam().getId()).isEqualTo(team.getId());
  }

}
