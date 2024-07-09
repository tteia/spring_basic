package com.beyond.basic.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 기존에 설정했던 getter 와 setter 를 전부 지워주고,
// @Getter, @Setter > lombok 어노테이션(annotation) 사용 !
//@Getter
//@Setter
@Data // getter, setter, toString 등을 포함
//@NoArgsConstructor : 기본 생성자 넣어주는 거 !
//@AllArgsConstructor : 모든 매개변수를 사용한 생성자를 만드는 어노테이션.
public class Hello {
    private String name;
    private String email;
    private String password;

    // sout(hello); > 주소가 나옴 > 값이 나오게 하려면?
    // toString 오버라이딩 필요
//    @Override
//    public String toString(){
//        return "이름은 " + this.name;
//    }
}
