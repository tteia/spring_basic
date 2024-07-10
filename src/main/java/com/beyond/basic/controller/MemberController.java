package com.beyond.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.beyond.basic.domain.Member;


@Controller
public class MemberController {

    // 회원 목록 조회
    @GetMapping("/member/list")
    public String memberList(){
        // 화면 하나 만들어서 memberList 메서드에서 해당 화면 리턴
        // 화면명 : member-list / title "회원목록조회"
        return "member/member-list";
    }

    // 회원 상세 조회
    // url(uri) : member/1, member/2
    // 화면명 : member-detail
    @GetMapping("/member/member/{inputID}")
    // int 또는 long 으로 받을 경우 Spring 에서 형 변환 (String -> Long)
    public String memberDetail(@PathVariable Long inputID){
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
    public String memberCreatePost(@ModelAttribute Member member){
        return null;
    }

}
