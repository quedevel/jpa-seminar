package que.jpa.seminar.repository;


import static org.assertj.core.api.Assertions.assertThat;
import static que.jpa.seminar.entity.QMember.member;

import com.querydsl.core.Tuple;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import que.jpa.seminar.dto.MemberDto;
import que.jpa.seminar.entity.Member;
import que.jpa.seminar.entity.Team;

@SpringBootTest
@Transactional
class RepositoryTest {

  @Autowired
  MemberRepository memberRepository;
  @Autowired
  TeamRepository teamRepository;


  @BeforeEach
  void before(){
    Team teamA = new Team("teamA");
    Team teamB = new Team("teamB");
    teamRepository.save(teamA);
    teamRepository.save(teamB);

    Member member1 = new Member("member1", 10, teamA);
    Member member2 = new Member("member2", 20, teamA);
    Member member3 = new Member("member3", 30, teamB);
    Member member4 = new Member("member4", 40, teamB);
    memberRepository.save(member1);
    memberRepository.save(member2);
    memberRepository.save(member3);
    memberRepository.save(member4);
  }

  @Test
  void whereJPQL(){
    //given
    String name = "member1";

    //when
    List<Member> members = memberRepository.selectMemberByName(name);

    //then
    assertThat(members).isNotEmpty();
    assertThat(members.size()).isEqualTo(1);
  }

  @Test
  void joinJPQL(){
    //given
    String name = "teamA";

    //when
    List<Member> members = memberRepository.selectMemberByTeamName(name);

    //then
    assertThat(members).isNotEmpty();
    assertThat(members.size()).isEqualTo(2);
  }

  @Test
  void groupJPQL(){
    //when
    List<jakarta.persistence.Tuple> tuples = memberRepository.selectCount();
    jakarta.persistence.Tuple tuple = tuples.get(0);

    //then
    System.out.println(tuple.get(0));
    System.out.println(tuple.get(1));
    System.out.println(tuple.get(2));
    System.out.println(tuple.get(3));
    System.out.println(tuple.get(4));
  }

  @Test
  void searchQuerydsl(){
    //given
    String name = "member1";
    int age = 10;

    //when
    List<Member> members1 = memberRepository.selectMemberByNameAndAge1(name, age);
    List<Member> members2 = memberRepository.selectMemberByNameAndAge2(name, age);

    //then
    assertThat(members1).isNotEmpty();
    assertThat(members2).isNotEmpty();
    assertThat(members1.get(0).getName()).isEqualTo(members2.get(0).getName());
  }

  @Test
  void aggregation() {
    List<Tuple> fetch = memberRepository.aggregation();

    Tuple tuple = fetch.get(0);
    assertThat(tuple.get(member.count())).isEqualTo(4);
    assertThat(tuple.get(member.age.sum())).isEqualTo(100);
    assertThat(tuple.get(member.age.avg())).isEqualTo(25);
    assertThat(tuple.get(member.age.max())).isEqualTo(40);
    assertThat(tuple.get(member.age.min())).isEqualTo(10);
  }

  @Test
  void join() {
    List<Member> fetch = memberRepository.join("teamA");

    assertThat(fetch)
        .extracting("name")
        .containsExactly("member1","member2");
  }

  @Test
  void theta_join() {
    List<Member> fetch = memberRepository.thetaJoin("teamA");

    assertThat(fetch)
        .extracting("name")
        .containsExactly("member1","member2");
  }

  @Test
  void where_subQuery() {
    List<Member> fetch = memberRepository.where_subQuery();

    assertThat(fetch)
        .extracting("age")
        .containsExactly(40);
  }

  @Test
  void result_subQuery() {
    List<Tuple> fetch = memberRepository.result_subQuery();

    for (Tuple tuple : fetch) {
      System.out.println("tuple = " + tuple);
    }
  }

  @Test
  void complexCase() {
    List<String> etc = memberRepository.complexCase();

    for (String s : etc) {
      System.out.println("s = " + s);
    }
  }

  @Test
  void constant() {
    List<Tuple> tuples = memberRepository.constant();

    for (Tuple tuple : tuples) {
      System.out.println("tuple = " + tuple);
    }
  }

  @Test
  void concat() {
    List<String> fetch = memberRepository.concat();

    for (String s : fetch) {
      System.out.println("s = " + s);
    }
  }

  @Test
  void findDtoByJPQL() {
    List<MemberDto> resultList = memberRepository.findDtoByJPQL();

    for (MemberDto memberDto : resultList) {
      System.out.println("memberDto = " + memberDto);
    }
  }

  @Test
  void findDtoBySetter() {
    List<MemberDto> fetch = memberRepository.findDtoBySetter();

    for (MemberDto memberDto : fetch) {
      System.out.println("memberDto = " + memberDto);
    }
  }

  @Test
  void findDtoByField() {
    List<MemberDto> fetch = memberRepository.findDtoByField();

    for (MemberDto memberDto : fetch) {
      System.out.println("memberDto = " + memberDto);
    }
  }

  @Test
  void findDtoByConstructor() {
    List<MemberDto> fetch = memberRepository.findDtoByConstructor();

    for (MemberDto memberDto : fetch) {
      System.out.println("memberDto = " + memberDto);
    }
  }

  @Test
  void findDtoByQueryProjection() {
    List<MemberDto> result = memberRepository.findDtoByQueryProjection();

    for (MemberDto memberDto : result) {
      System.out.println("memberDto = " + memberDto);
    }
  }

  @Test
  void dynamicQuery_BooleanBuilder() {
    String usernameParam = "member1";
    Integer ageParam = 10;

    List<Member> result = memberRepository.dynamicQuery(usernameParam, ageParam);
    assertThat(result.size()).isEqualTo(1);
  }

  @Test
  void nativeSQL(){
    List<Member> result = memberRepository.nativeSQL();
    assertThat(result).isNotEmpty();
  }

  @Test
  void sqlFunction() {
    List<String> list = memberRepository.sqlFunction();

    for (String s : list) {
      System.out.println("s = " + s);
    }
  }

}