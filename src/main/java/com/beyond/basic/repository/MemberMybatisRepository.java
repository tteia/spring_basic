//package com.beyond.basic.repository;
//
//import com.beyond.basic.domain.Member;
//import org.apache.ibatis.annotations.Mapper;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//@Mapper // 해당 Repository 는 mybatis 로 이루어진 Repository 다 ! 라고 선언하는 표현.
//
//
//public interface MemberMybatisRepository extends MemberRepository{ // 인터페이스 간 상속 관계 선언
//    // 인터페이스니까 쿼리는 구현 안 하고 따로 빼준다 -> MemberMapper.xml 에 !
////    List<Member> findAll();
////
////    Member save();
////    Member findById(Long id);
//}
