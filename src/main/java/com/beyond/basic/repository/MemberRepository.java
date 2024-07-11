package com.beyond.basic.repository;

import com.beyond.basic.domain.Member;

import java.util.List;

// 인터페이스 -> 로직이 안 들어감.
public interface MemberRepository {
    // 나 회원가입 할게 ~ -> 이름,이메일, 비번 받음 -> C에서 받음
    // S로 넘겨줌 -> R로 넘겨서 R 에서는 DB로 또 보냄 ->  테이블 생성 완료 ~

    Member save(Member member); // save 해온 멤버 가져오기.

    List<Member> findAll(); // select * from member; >> 전체값을 찾아오는거니까 변수가 더 붙지않음.

    Member findById(Long id);

}
