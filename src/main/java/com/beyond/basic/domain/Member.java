package com.beyond.basic.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//@Data
//entity어노테이션을 통해 엔티티매니저에게 객체관리를 위임
//해당 클래스명으로 테이블 및 컬럼을 자동생성하고 각종 설정정보 위임
@Entity
//@AllArgsConstructor
@NoArgsConstructor // 기본 생성자는 JPA 에서 필수.
@Getter
public class Member extends BaseEntity {
    @Id //pk설정
//    identity : auto_increment설정
//    auto : jpa 자동으로 적절한 전략을 선택하도록 맡기는것.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //long은 bigint로 변환
//    String은 Varchar(255)로 기본으로 변환. name변수명이 name컬럼명으로 변환.
    private String name;
//    nullable=false : not null 제약조건
    @Column(nullable = false, length = 50, unique = true)
    private String email;
//    @Column(name = "pw") 이렇게 할수 있으나, 컬럼명과 변수명을 일치시키는것이 혼선을 줄일수 있음
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Post> postList;

//    // 처음 생성될 때 (최초의) 시간.
//    @CreationTimestamp // DB 에는 current_timestamp 가 생성되지 않음. => DB 에는 insert 로 넣어줘야 됨. 무조건 JPA 를 지나야먄 DB에 들어갈 수 있게끔,,
//    private LocalDateTime createdTime; // camelCase 사용 시 DB 에는 _ (언더바)로 생성.
//
//    // 업데이트 되었을 때의 시간.
//    @UpdateTimestamp // 추가는 되는데 변경은 안 됨.
//    private LocalDateTime updateTime;

    public Member(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //password 상단에 @Setter 를 통해 특정 변수만 setter 사용이 가능하나,
    // 일반적으로 의도를 명확하게 한 메서드를 별도로 만들어 사용하는 것을 권장.
    public void updatePw(String password){
        this.password = password;
    }

    public MemberDetResDto detFromEntity(){
        LocalDateTime createdTime = this.getCreatedTime();
        String value = createdTime.getYear()+"년 "+createdTime.getMonthValue()+"월 "+createdTime.getDayOfMonth()+"일";
        return new MemberDetResDto(this.id, this.name, this.email, this.password, value);
    }

    public MemberResDto listFromEntity(){
        return new MemberResDto(this.id, this.name, this.email);
    }

}
