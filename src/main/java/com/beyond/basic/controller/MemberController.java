package com.beyond.basic.controller;

import com.beyond.basic.domain.Member;
import com.beyond.basic.domain.MemberDetResDto;
import com.beyond.basic.domain.MemberReqDto;
import com.beyond.basic.domain.MemberResDto;
import com.beyond.basic.service.MemberService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
//@RequiredArgsConstructor
public class MemberController {

//    의존성주입(DI)방법1. 생성자주입방식(가장많이사용하는방식)
//    장점:1)final을통해 상수로 사용가능 2)다형성구현가능 3)순환참조방지
    // 생성자가 하나일 때는 autowired 생략 가능
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
////    의존주입방법2. 필드주입방식(Authorwired만 사용)
//    @Autowired
//    private MemberService memberService;

//      의존성주입방법3. 어노테이션(RequiredArgs)을 이용하는 방식
//    @RequiredArgsConstructor: @NonNull어노테이션, final 키워드가 붙어있는 필드를 대상으로 생성자 생성
//    private final MemberService memberService;
    @GetMapping("/")
    public String home(){
        return "member/home";
    }
//    회원목록조회
    @GetMapping("/member/list")
    public String memberList(Model model){
        List<MemberResDto> memberList = memberService.memberList();
        model.addAttribute("memberList", memberList);
        return "member/member-list";
    }


//    회원상세조회 : memberDetail
//    url(uri) : member/1, member/2
//    화면명 : member-detail
    @GetMapping("/member/detail/{id}")
//    int 또는 long 받을 경우 스프링에서 형변환(String->Long)
    public String memberDetail(@PathVariable Long id, Model model){
        MemberDetResDto memberDetResDto = memberService.memberDetail(id);
        model.addAttribute("member", memberDetResDto);
        return "/member/member-detail";
    }

    @GetMapping("/member/create")
    public String memberCreate(){
        return "member/member-create";
    }

    @PostMapping("/member/create")
    public String memberCreatePost(MemberReqDto dto, Model model){
        try {
            memberService.memberCreate(dto);
//        화면 리턴이아닌 url 재호출
            return "redirect:/member/list";
        }catch (IllegalArgumentException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/member-error";
        }
    }


    
}
