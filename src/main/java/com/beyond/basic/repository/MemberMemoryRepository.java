//package com.beyond.basic.repository;
//
//import com.beyond.basic.domain.Member;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//// 2. Repository 라는 어노테이션 존재.
//// 해당 클래스가 Repository 계층임을 표현함과 동시에 싱글톤 객체로 생성.
//// new 키워드는 더 이상 쓰지 않는다 ! 어노테이션 덕분에 보이지 않아도 new 가 함께 존재함.
//@Repository
//
//// 1. implements MemberRepository > 오버라이딩 하세요 > 네
//public class MemberMemoryRepository implements MemberRepository{
//    // b. 메모리에 저장하기 위해 리스트 생성.
//    // 한 번만 호출됨 > 초기화 되지 않음.
//    // 확실하게 하기 위해 생성자에 넣어준다.
//
//    private final List<Member> memberList;
//
//    // c. 생성자로 활용하는 것이 더 일반적. 생성자에 써주기!
//    MemberMemoryRepository(){
//        this.memberList = new ArrayList<>();
//    }
//
//    @Override
//    public Member save(Member member) {
//        // a. 메모리에 저장할게 ! (현재 우리는 db 가 없으니까.)
//        memberList.add(member);
//        return member;
//    }
//
//    @Override
//    public List<Member> findAll() {
//        return memberList;
//    }
//
//    //staticId ++ 써야되는데 시간 활용을 위해 생략
//    @Override
//    public Member findById(Long id) {
//        return null;
//    }
//
//    // 위 세 개 오버라이딩 자동 생성 됨.
//}
