//package com.beyond.basic.repository;
//
//import com.beyond.basic.domain.Member;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//
//@Repository
//public class MemberJpaRepository implements MemberRepository{
//    // EntityManager 는 JPA 의 핵심 클래스 (객체)
//    // Entity 의 생명 주기를 관리, 데이터베이스와의 모든 인터페이싱을 담당.
//    // 즉, 엔티티를 대상으로 CRUD 하는 기능을 제공.
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @Override
//    public Member save(Member member) {
//        // persist : 전달된 엔티티(Member)가 EntityManager 의 관리상태가 되도록 만들어주고,
//        // 트랜잭션이 커밋될 때 데이터베이스에 저장, insert.
//        entityManager.persist(member);
//        return null;
//    }
//
//    @Override
//    public List<Member> findAll() {
//        // jpql : jpa 의 raw 쿼리 문법(정확하게는 객체지향 쿼리 문법.)
//        // jpa 에서는 jpql 문법 컴파일 에러가 나오지 않으나, springdatajpa 에서는 컴파일 에러 발생.
//        List<Member> memberList = entityManager.createQuery("SELECT m FROM Member m", Member.class).getResultList();
//        return memberList;
//    }
//
//    @Override
//    public Member findById(Long id) {
//        // entityManager 를 통해 find => 상단에 private 로 선언.
//        // 리턴 타입 클래스 지정 및 매개변수로 pk 필요.
//        // 쿼리를 짤 필요가 없이 EntityManager 사용
//        Member member = entityManager.find(Member.class, id);
//        return member;
//    }
//
////    public Member findByEmail(String inputEmail){
////        Member member = entityManager
////                .createQuery("SELECT m FROM Member m WHERE m.email= :email", Member.class)
////                .setParameter("email", inputEmail).getSingleResult(); // 아까는 resultList. 만약 name 이면 resultList ! email은 유니크니까 single 가능.
////        return member;
////    }
//}
