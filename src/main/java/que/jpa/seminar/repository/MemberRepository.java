package que.jpa.seminar.repository;

import jakarta.persistence.Tuple;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import que.jpa.seminar.dto.MemberDto;
import que.jpa.seminar.entity.Member;
import que.jpa.seminar.repository.querydsl.CustomizedMemberRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, CustomizedMemberRepository {


  // JPQL를 이용한 기본 예제

  /**
   * 기본 조회
   */
  @Query("select m from Member m where m.name = :name")
  List<Member> selectMemberByName(String name);

  /**
   * join 문
   */
  @Query("select m from Member m join Team t on m.team = t where t.name = :name")
  List<Member> selectMemberByTeamName(String name);

  /**
   * 집합
   */
  @Query("select count(m), sum(m.age), avg(m.age), max(m.age), min(m.age) from Member m join Team t on m.team = t group by t.name")
  List<Tuple> selectCount();

  @Query("select new que.jpa.seminar.dto.MemberDto(m.name,m.age) from Member m")
  List<MemberDto> findDtoByJPQL();

  @Query(value = "select * from Member", nativeQuery = true)
  List<Member> nativeSQL();
}
