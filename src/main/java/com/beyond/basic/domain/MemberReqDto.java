package com.beyond.basic.domain;

import lombok.Data;

@Data
public class MemberReqDto {
    private String name;
    private String email;
    private String password;

    // dto 에서 entity 로 변환. -> req // 반대 경우면 res => member
    // 추후에는 빌더패턴으로 변환.
    public Member toEntity(){
        Member member = new Member(this.name, this.email, this.password);
        return member;
    }
}
