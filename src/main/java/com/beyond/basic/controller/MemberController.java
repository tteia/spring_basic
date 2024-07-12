package com.beyond.basic.controller;

import com.beyond.basic.domain.MemberDetailDto;
import com.beyond.basic.domain.MemberReqDto;
import com.beyond.basic.domain.MemberResDto;
import com.beyond.basic.service.MemberService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.beyond.basic.domain.Member;

import java.util.List;


@Controller
//@RequiredArgsConstructor
public class MemberController {
    @GetMapping("/")
    public String home(){
        return "member/home";
    }
    // 4. 최상단으로 올려줬으나 똑같이 싱글톤 처리가 필요하다.
    // 5. MemberService 로 가서 어노테이션 써주기.
    // 6.

    // 의존성 주입(DI) 방법
    // 1. 생성자 주입 방식 (가장 많이 사용하는 방식)
    // 장점 1) final 을 통해 상수로 사용 가능
    // 2) 다형성 구현 가능
    // 3) 순환 참조 방지

    private final MemberService memberService;
//
//    // 7. 마찬가지로 new 가 없다 ! DI 의존성 해주는 것. (주입)
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService  = memberService;
    }

    // 의존성 주입 방법 2. 필드 주입 방식(Autowired 만 사용)
    // new 는 아님. 저장된 데이터를 주입 받는 방식이다.
    // 1번이랑 동일한 방식.
    // 주입 받기 때문에 final 을 붙일 수가 없다 -> 재할당이 가능해져버림,,
//    @Autowired
//    private MemberService memberService;

    // 의존성 주입 방법 3. 어노테이션(RequireArgs)을 이용하는 방식.
    // 생성자 생성하는 어노테이션 > no & all 있는 거. . . 잊지말기 . .
    // @RequiredArgsConstructor : @NonNull 어노테이션, final 키워드가 붙은 필드를 대상으로 생성자 생성.
    // 클래스 위에 @Require... 써줌
    // 반드시 초기화 되어야하는 아이들한테 생성자 만들어줄게 ~ 하는 어노테이션임.
    // @NonNull // 초기화 되어있는데 비어있으면 안돼 ~! final 로 대체 가능함.
//    private final MemberService memberService;


    // 회원 목록 조회
    @GetMapping("/member/list")
    public String memberList(Model model){
        List<MemberResDto> memberList = memberService.memberList();
        model.addAttribute("memberList", memberList);
        // 화면 하나 만들어서 memberList 메서드에서 해당 화면 리턴
        // 화면명 : member-list / title "회원목록조회"
        return "member/member-list";
    }

    // 회원 상세 조회
    // url(uri) : member/1, member/2
    // 화면명 : member-detail
    @GetMapping("/member/detail/{inputID}")
    // int 또는 long 으로 받을 경우 Spring 에서 형 변환 (String -> Long)
    public String memberDetail(@PathVariable Long inputID, Model model){
        MemberDetailDto memberDetail = memberService.memberDetail(inputID);
        model.addAttribute("memberDetail", memberDetail);
        return "member/member-detail";
    }

    // 회원 가입 화면 주고, -> GetMapping
// url : member/create
    @GetMapping("/member/create")
    public String memberCreate(){
        return "member/member-create";
    }

    // 회원 가입 데이터를 받기. -> PostMapping
// url : member/create
// name, email, password
    @PostMapping("/member/create")
//    @ResponseBody // 성공하면 다른 화면으로 가게 할 거라서 뺄게여
    public String memberCreatePost(MemberReqDto dto, Model model){
        try{
            memberService.memberCreate(dto);
            // 화면 리턴이 아닌 url 재호출
            return "redirect:/member/detail";
        }
        catch(IllegalArgumentException e){ //MemberService -> memberCreate 에서 작성한 e
            //e.getMessage();
            model.addAttribute("errorMessage", e.getMessage());
            return "member/member-error";
        }

        // 1. 디테일이랑 크리에이트 모두 멤버서비스가 필요하다.
        // 2. 조회하는데에 필요하니까 !
        // 3. 최상단으로 보낼게요 .
//        memberService.memberCreate(member);

        // return "member/list" 하면 안되나요? -> 데이터가 빠져있음(모델이 데이터를 끼워줘야하는데)
        // 아예 url 로 이동되어야 함.
//        return "redirect:/member/list";
    }

}
