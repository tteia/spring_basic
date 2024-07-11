package com.beyond.basic.domain;

import lombok.Data;

@Data
// Data 가 없으면 ..? 데이터 바인딩이 안됨.
public class MemberReqDto {
    private String name;
    private String email;
    private String password;
}
