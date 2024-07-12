//package com.beyond.basic.repository;
//
//import com.beyond.basic.domain.Member;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//// 이름은 SpringDataJpaRepository 지만 결국 나중에는 얘가!!! MemberRepositoty 가 될 거야, . . . .
//
//// MemberRepository(SpringDataJpaRepository) 가 되기 위해서는 JpaRepository 를 상속해야 하고,
//// 상속시에 Entity 명과 entity 의 PK 타입을 명시함. (Generic 에다가!)
//// MemberRepository 의 리턴 타입이 옵셔널이 아니라서 타입이 맞지 않아 빨간 줄 뜬다,,
//// 다형성 구현 안 할거니까 멤버레파지토리 지워버림.
//
//// MemberRepository 는 JpaRepository 를 상속함으로서 JpaRepository 를 상속함으로서 JpaRepository 의 주요 기능을 상속한다.
//// JpaRepository 가 인터페이스임에도 해당 기능을 상속해서 사용할 수 있는 이유는 hibernate 구현체가 미리 구현되어 있기 때문이다.
//// 런타임 시점에 사용자가 인터페이스에 정의한 메서드를 프록시(대리인) 객체가 리플렉션 기술을 통해 메서드를 구현(주입)한다. ⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️
//// 예를 들면 findbyId는 된다! 왜냐. 값이 사용자에 의해 변하는 게 아니니까.
//// 대신 findByName 이나 email 은 우리가 써줘야댐.
//// 근데 ! 그것도 아래처럼 선언만하면 알아서 해줌. 런타임 시점에 실시간으로 만들어줌. => 이 때 사용되는 기술이 리플렉션 기술이다.
//public interface MyMemberRepository extends JpaRepository<Member, Long> {
//    Optional<Member> findByEmail(String email);
//}