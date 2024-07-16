package com.beyond.basic.service;

import com.beyond.basic.controller.MemberController;
import com.beyond.basic.domain.*;
import com.beyond.basic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//        input값의 검증 및 실질적인 비지니스 로직은 서비스 계층에서 수행
@Service //서비스 계층임을 표현함과 동시에 싱글톤객체로 생성
//Transactional어노테이션을 통해 모든 메서드에 트랜잭션을 적용하고,(각 메서드마다 하나의트랜잭션으로 묶는다는뜻)
//만약 예외가 발생시 롤백처리 자동화
@Transactional(readOnly = true) // 모든 메서드가 readOnly => 조회용이라고 알림.
public class MemberService {
////    다형성 설계
//    private final MemberRepository memberRepository;
//    @Autowired //싱글톤객체를 주입(DI) 받는다라는 것을 의미
//    public MemberService(MemberSpringDataJpaRepository memoryRepository){
//        this.memberRepository = memoryRepository;
//    }

//    비다형성 설계
    private final MyMemberRepository memberRepository;
    @Autowired //싱글톤객체를 주입(DI) 받는다라는 것을 의미
    public MemberService(MyMemberRepository memoryRepository){
        this.memberRepository = memoryRepository;
    }


    public void memberCreate(MemberReqDto dto){
        if(dto.getPassword().length() < 8){
            throw new IllegalArgumentException("비밀번호가 너무 짧습니다.");
        }
        Member member = dto.toEntity();
        memberRepository.save(member);

//        // Transactional 롤백 테스트
//        // 상단에 @Transactional 선언이 되어 있기 때문에 트랜잭션으로 돌아가게 된다.
//        if(dto.getName().equals("kin")){
//            throw new IllegalArgumentException("잘못된 입력입니다.");// 예외 발생 시 위 save 가 롤백 됨.
//        }

    }
    public MemberDetResDto memberDetail(Long id){
        Optional<Member> optMember = memberRepository.findById(id);
//        클라이언트에게 적절한 예외메시지와 상태코드를 주는것이 주요목적
//        또한, 예외를 강제 발생시킴으로서 적절한 롤백처리 하는것도 주요목적
        Member member = optMember.orElseThrow(()->new EntityNotFoundException("없는 회원입니다."));
        System.out.println("글쓴이의 글 쓴 개수 : " + member.getPostList().size());
        for (Post p : member.getPostList()) {
            System.out.println("글의 제목 : " + p.getTitle());
        }
        return member.detFromEntity();
    }
    public List<MemberResDto> memberList(){
        List<MemberResDto> memberResDtos = new ArrayList<>();
        List<Member> memberList =  memberRepository.findAll();
        for(Member member : memberList){
            memberResDtos.add(member.listFromEntity());
        }
        return memberResDtos;
    }

    public void pwUpdate(MemberUpdateDto dto){
        Member member = memberRepository.findById(dto.getId()).orElseThrow(()->new EntityNotFoundException("멤버를 찾을 수 없습니다."));
        member.updatePw(dto.getPassword());

        // 기존 객체를 조회 후 수정한 다음에 save 시에는 jpa_update 실행
        memberRepository.save(member);
    }

    public void delete(Long id){
        Member member = memberRepository.findById(id).orElseThrow(()->new EntityNotFoundException("멤버를 찾을 수 없습니다."));
        memberRepository.delete(member); // 완전 삭제

        //실제 삭제
//        member.updateDelYN("Y");
//        memberRepository.save(member);
    }
}
