package que.jpa.seminar;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import que.jpa.seminar.entity.Member;
import que.jpa.seminar.entity.Team;

@SpringBootApplication
public class JpaSeminarApplication {

  public static void main(String[] args) {
    SpringApplication.run(JpaSeminarApplication.class, args);
  }

  @Bean
  public ApplicationRunner applicationRunner(){
    return new ApplicationRunner() {

      @PersistenceContext
      EntityManager em;

      @Override
      @Transactional
      public void run(ApplicationArguments args) throws Exception {
        Team team1 = Team.builder().id(1L).name("team1").build();
        em.persist(team1);

        Member user1 = Member.builder().id(1L).username("user1").team(team1).build();
        Member user2 = Member.builder().id(2L).username("user2").team(team1).build();
        em.persist(user1);
        em.persist(user2);

        em.flush();
        em.clear();
      }
    };
  }

}
