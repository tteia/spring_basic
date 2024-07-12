package com.beyond.basic.domain;

import lombok.Data;

import javax.persistence.*;

@Data
// Entity 어노테이션을 통해 EntityManager 에게 객체 관리를 위임한다.
// 해당 클래스 명으로 테이블 및 컬럼을 자동 생성하고, 각종 설정 정보를 위임한다.
@Entity
public class Member {
    @Id // pk 설정 !

    // identity : auto_increment 설정
    // auto 랑 헷갈리지 말 것 ! auto : jpa 가 자동으로 적절한 전략을 선택하도록 맡기는 것.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Long -> bigint 로 변환됨.

    // String 은 varchar(255) 로 기본으로 변환. name 변수명이 name 컬럼명으로 변환.
    // 255 가 싫다면 @Column 으로 일일히 변경 가능.
    private String name;
    // nullabe => notnull, unique => unique
    @Column(nullable = false, length = 50, unique = true)
    private String email;
    // @Column(name = 'pw') 로 설정할 수 있으나, 컬럼명과 변수명을 통일시키는 것이 좋다.
    private String password;
}
