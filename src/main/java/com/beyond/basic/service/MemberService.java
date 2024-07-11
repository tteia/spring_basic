package com.beyond.basic.service;

import com.beyond.basic.controller.MemberController;
import com.beyond.basic.domain.Member;
import com.beyond.basic.domain.MemberReqDto;
import com.beyond.basic.domain.MemberResDto;
import com.beyond.basic.repository.MemberJdbcRepository;
import com.beyond.basic.repository.MemberMemoryRepository;
import com.beyond.basic.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// 실질적인 로직을 담당하는 Service.
// input 값의 검증 및 실질적 비지니스 로직은 Service 계층에서 수행.

// 서비스 어노테이션 : 서비스 계층임을 표현함과 동시에 싱글톤 객체로 생성.
@Service
public class MemberService {
    // 1. 메서드마다 선언해주니까 (중복) 아예 최상단에 선언 !
    // 2. 생성자가 호출될 때마다 new 객체 => 생성자에 선언.
    // 3. 메서드마다 선언해주니까 최상단에 가져옴. 그럼 클래스 차원으로 확장은 ? => 싱글톤
    // 4. MemberMemoryRepository 에서 어노테이션 선언해주고 옴.

    // 두 번 할당되지 않도록 final.
    // 다른 클래스에서 적용되지 않도록 private.
    private final MemberRepository memberRepository;

    // 5. Autowired : <싱글톤 객체를 주입(DI) 받는다> 라는 것을 의미함.
    @Autowired
    public MemberService(MemberJdbcRepository memoryRepository){
        // 생성자가 호출될 때 할래! -> 상단에 선언되어있던 걸 가져오기
        // 이름이 충돌날 경우가 없기 때문에 this 는 생략 가능.
         this.memberRepository = memoryRepository;
    }
//
//    @Autowired
//    private MemberController memberController;

    public void memberCreate(MemberReqDto dto){
        if(dto.getPassword().length() < 8){
            throw new IllegalArgumentException("비밀번호가 너무 짧습니다.");
        }
        // 검증 후에는 Repository 로 넘겨줄거야.
//        memberRepository.save(member);

        // dto 로 바꿔주기. 메서드 인풋에서 req 로 바꿔줌.
        Member member = new Member();
        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
        member.setPassword(dto.getPassword());
        memberRepository.save(member);
    }

    public Member memberDetail(Long id){
        return memberRepository.findById(id);
    }

    public List<MemberResDto> memberList(){
        // 회원 목록 조회할 때 비밀번호빼고 조회되었으면 좋겠다.
        List<MemberResDto> memberResDtos = new ArrayList<>();
        List<Member> memberList = memberRepository.findAll();
        for (Member member : memberList) {
            MemberResDto dto = new MemberResDto();
            dto.setName(member.getName());
            dto.setEmail(member.getEmail());
            memberResDtos.add(dto);
        }
        return memberResDtos;
    }
}
