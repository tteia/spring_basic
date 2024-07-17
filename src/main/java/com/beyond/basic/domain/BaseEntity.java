package com.beyond.basic.domain;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 이것만 가지고는 객체 생성 안돼 ! => abstract 추상 클래스.
@Getter // Member 의 에러 알림이 없어짐.
@MappedSuperclass // 기본적으로 Entity 는 상속 관계가 불가능하여, 해당 어노테이션을 붙여야 상속 관계 성립 가능.
public abstract class BaseEntity {

    @CreationTimestamp
    private LocalDateTime createdTime;


    @UpdateTimestamp
    private LocalDateTime updateTime;

}
