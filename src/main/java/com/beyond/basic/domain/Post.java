package com.beyond.basic.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne // 1:1 의 경우 OneToOne , unique=true;
    @JoinColumn(name = "member_id")
    // JPA 의 영속성(Persistence) 컨텍스트에 의해 Member 가 관리됨.
    private Member member;

    public PostResDto fromEntity(){
        return new PostResDto(this.id, this.title);
    }
}
