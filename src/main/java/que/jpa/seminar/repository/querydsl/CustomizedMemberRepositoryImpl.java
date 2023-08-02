package que.jpa.seminar.repository.querydsl;

import static com.querydsl.jpa.JPAExpressions.select;
import static que.jpa.seminar.entity.QMember.member;
import static que.jpa.seminar.entity.QTeam.team;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import que.jpa.seminar.dto.MemberDto;
import que.jpa.seminar.dto.QMemberDto;
import que.jpa.seminar.entity.Member;
import que.jpa.seminar.entity.QMember;

public class CustomizedMemberRepositoryImpl implements CustomizedMemberRepository {

  private final JPAQueryFactory queryFactory;

  public CustomizedMemberRepositoryImpl(JPAQueryFactory queryFactory) {
    this.queryFactory = queryFactory;
  }


  @Override
  public List<Member> selectMemberByNameAndAge1(String name, int age) {
    // where 조건절에 and를 체이닝으로 선언
    return queryFactory
        .selectFrom(member)
        .where(member.name.eq(name).and(member.age.eq(age)))
        .fetch();
  }

  @Override
  public List<Member> selectMemberByNameAndAge2(String name, int age) {
    // where 조건절에 and를 , 로 나열
    return queryFactory
        .selectFrom(member)
        .where(member.name.eq(name), member.age.eq(age))
        .fetch();
  }

  @Override
  public List<Tuple> aggregation() {
    return queryFactory
        .select(member.count(),
            member.age.sum(),
            member.age.avg(),
            member.age.max(),
            member.age.min())
        .from(member)
        .fetch();
  }

  @Override
  public List<Member> join(String teamName) {
    return queryFactory
        .selectFrom(member)
        .leftJoin(member.team, team)
        .where(team.name.eq(teamName))
        .fetch();
  }

  @Override
  public List<Member> thetaJoin(String teamName) {
    return queryFactory
        .select(member)
        .from(member, team)
        .where(member.team.name.eq(team.name),team.name.eq(teamName))
        .fetch();
  }

  @Override
  public List<Member> where_subQuery() {
    QMember sub = new QMember("sub");

    return queryFactory
        .selectFrom(member)
        .where(member.age.eq(
            select(sub.age.max())
                .from(sub)
        ))
        .fetch();
  }

  @Override
  public List<Tuple> result_subQuery() {

    QMember sub = new QMember("sub");

    return queryFactory
        .select(member.name,
            select(sub.age.avg()).from(sub))
        .from(member)
        .fetch();
  }

  @Override
  public List<String> complexCase() {
    return queryFactory
        .select(new CaseBuilder()
            .when(member.age.between(0, 20)).then("0~20")
            .when(member.age.between(21, 30)).then("21~30")
            .otherwise("etc"))
        .from(member)
        .fetch();
  }

  @Override
  public List<Tuple> constant() {
    return queryFactory
        .select(member.name, Expressions.constant("A"))
        .from(member)
        .fetch();
  }

  @Override
  public List<String> concat() {
    return queryFactory
        .select(member.name.concat("_").concat(member.age.stringValue()))
        .from(member)
        .fetch();
  }

  @Override
  public List<MemberDto> findDtoBySetter() {
    return queryFactory
        .select(Projections.bean(MemberDto.class,
            member.name,
            member.age))
        .from(member)
        .fetch();
  }

  @Override
  public List<MemberDto> findDtoByField() {
    return queryFactory
        .select(Projections.fields(MemberDto.class,
            member.name,
            member.age))
        .from(member)
        .fetch();
  }

  @Override
  public List<MemberDto> findDtoByConstructor() {
    return queryFactory
        .select(Projections.constructor(MemberDto.class,
            member.name,
            member.age))
        .from(member)
        .fetch();
  }

  @Override
  public List<MemberDto> findDtoByQueryProjection() {
    return queryFactory
        .select(new QMemberDto(member.name, member.age))
        .from(member)
        .fetch();
  }

  @Override
  public List<Member> dynamicQuery(String name, Integer age) {
    BooleanBuilder builder = new BooleanBuilder();
    if (name != null) builder.and(member.name.eq(name));
    if (age != null) builder.and(member.age.eq(age));
    return queryFactory
        .selectFrom(member)
        .where(builder)
        .fetch();
  }

  @Override
  public List<String> sqlFunction() {
    return queryFactory
        .select(Expressions.stringTemplate(
            "function('replace',{0},{1},{2})",
            member.name, "member", "M"
        ))
        .from(member)
        .fetch();
  }
}
