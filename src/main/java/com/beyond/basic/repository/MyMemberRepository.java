package com.beyond.basic.repository;

import com.beyond.basic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyMemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    List<Member> findByName(String name);

//    // jpql 문법을 통한 raw 쿼리 작성 시 컴파일 타임에서 오류 체크.
//    @Query("select m from member m")
//    List<Member> myFindAll();
}
