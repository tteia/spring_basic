package com.beyond.basic.controller;

import com.beyond.basic.domain.MemberDetResDto;
import com.beyond.basic.domain.MemberReqDto;
import com.beyond.basic.domain.MemberResDto;
import com.beyond.basic.domain.MemberUpdateDto;
import com.beyond.basic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController // RestController 의 경우 모든 메서드 상단에 @ResponseBody 가 붙는 효과 발생.
@RequestMapping("/rest")
public class MemberRestController {

    private final MemberService memberService;
    @Autowired
    public MemberRestController(MemberService memberService){
        this.memberService = memberService;
    }

    // 회원 목록 조회
    @GetMapping("/member/list")
    public List<MemberResDto> memberList(){
        List<MemberResDto> memberList = memberService.memberList();
        return memberList;
    }


    @GetMapping("/member/detail/{id}")
    public MemberDetResDto memberDetail(@PathVariable Long id){ // url 에서 값 가져옴
        MemberDetResDto memberDetResDto = memberService.memberDetail(id);
        return memberDetResDto;
    }

    @PostMapping("/member/create")
    public String memberCreatePost(@RequestBody MemberReqDto dto){
        try {
            memberService.memberCreate(dto);
            return "ok";
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return "! ! error ! !";
        }
    }

    // 수정은 2가지 요청 방식 - PUT / PATCH 요청
    // patch 요청은 부분 수정, put 요청 덮어쓰기
    @PatchMapping("/member/pw/update")
    public String memberList(@RequestBody MemberUpdateDto dto){
        memberService.pwUpdate(dto);
        return "ok";
    }
    @DeleteMapping("/member/delete/{id}")
    public String memberDelete(@PathVariable Long id){
        memberService.delete(id);
        return "ok";
    }

    
}
