package com.beyond.basic.domain;

import lombok.*;

//롬복라이브러리를 통해 getter, setter 어노테이션 사용
//@Getter
//@Setter
@Data // getter, setter, toString 등을 포함
//@NoArgsConstructor : 기본생성자 만드는 어노테이션
//@AllArgsConstructor : 모든매개변수를 사용한 생성자를 만드는 어노테이션
public class Hello {
    private String name;
    private String email;
    private String password;

//    @Override
//    public String toString(){
//        return "이름은  : " + this.name + " email은 " + this.email;
//    }
}
