package com.beyond.basic.controller;

import com.beyond.basic.domain.*;
import com.beyond.basic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
//    @GetMapping("/member/list")
//    public List<MemberResDto> memberList(){
//        List<MemberResDto> memberList = memberService.memberList();
//        return memberList;
//    }

    @GetMapping("/member/list")
    public ResponseEntity<CommonResDto> memberList(){
        List<MemberResDto> memberList = memberService.memberList();
        CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "Success", memberList);
        ResponseEntity<CommonResDto> result = new ResponseEntity<>(commonResDto, HttpStatus.OK);
        return result;
    }


//    @GetMapping("/member/detail/{id}")
//    public MemberDetResDto memberDetail(@PathVariable Long id){ // url 에서 값 가져옴
//        MemberDetResDto memberDetResDto = memberService.memberDetail(id);
//        return memberDetResDto;
//    }

    @GetMapping("/member/detail/{id}")
    public Object memberDetail(@PathVariable Long id){
        try{
            MemberDetResDto memberDetResDto = memberService.memberDetail(id);
            CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "Success", memberDetResDto);
            ResponseEntity<CommonResDto> result = new ResponseEntity<>(commonResDto, HttpStatus.OK);
            return result;
        }
        catch (EntityNotFoundException e){
            CommonErrorDto commonErrorDto = new CommonErrorDto(HttpStatus.NOT_FOUND.value(), "Failed");
            ResponseEntity<CommonErrorDto> result = new ResponseEntity<>(commonErrorDto, HttpStatus.NOT_FOUND);
            return result;
        }
    }


//    @PostMapping("/member/create")
//    public String memberCreatePost(@RequestBody MemberReqDto dto){
//        try {
//            memberService.memberCreate(dto);
//            return "ok";
//        }catch (IllegalArgumentException e){
//            e.printStackTrace();
//            return "! ! error ! !";
//        }
//    }

    @PostMapping("/member/create")
    public Object memberCreatePost(@RequestBody MemberReqDto dto){
        try {
            memberService.memberCreate(dto);
            CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "Success", dto);
            ResponseEntity<CommonResDto> result = new ResponseEntity<>(commonResDto, HttpStatus.OK);
            return result;
        }catch (IllegalArgumentException e){
            CommonErrorDto commonErrorDto = new CommonErrorDto(HttpStatus.BAD_REQUEST.value(), "Failed");
            ResponseEntity<CommonErrorDto> result = new ResponseEntity<>(commonErrorDto, HttpStatus.BAD_REQUEST);
            return result;
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
