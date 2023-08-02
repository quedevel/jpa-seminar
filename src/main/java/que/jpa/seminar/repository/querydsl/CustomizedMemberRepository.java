package que.jpa.seminar.repository.querydsl;

import com.querydsl.core.Tuple;
import java.util.List;
import que.jpa.seminar.dto.MemberDto;
import que.jpa.seminar.entity.Member;

public interface CustomizedMemberRepository {

  List<Member> selectMemberByNameAndAge1(String name, int age);
  List<Member> selectMemberByNameAndAge2(String name, int age);

  List<Tuple> aggregation();

  List<Member> join(String teamName);

  List<Member> thetaJoin(String teamName);

  List<Member> where_subQuery();

  List<Tuple> result_subQuery();

  List<String> complexCase();

  List<Tuple> constant();

  List<String> concat();

  List<MemberDto> findDtoBySetter();

  List<MemberDto> findDtoByField();

  List<MemberDto> findDtoByConstructor();

  List<MemberDto> findDtoByQueryProjection();

  List<Member> dynamicQuery(String name, Integer age);

  List<String> sqlFunction();

}
