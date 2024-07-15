package com.beyond.basic.repository;

import com.beyond.basic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
//MemberRepository 되기 위해서는 JpaRepository를 상속해야하고, 상속시에 Entity명과 entity의 PK타입을 명시
//MemberRepository는 JpaRespository를 상속함으로서 JpaRepository의 주요 기능을 상속
//JpaRepository가 인터페이스임에도 해당 기능을 상속해서 사용할 수 있는 이유는 hiberante구현체가 미리 구현돼 있기 때문.
//런타임시점에 사용자가 인터페이스에 정의한 메서드를 리플렉션기술을 통해 메서드를 구현
public interface MemberSpringDataJpaRepository extends MemberRepository, JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
