package com.beyond.basic.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Student {
    private String name;
    private String email;
    // 그럼 private List<Grade> grades; 를 아래와 같이 수정.
    private List<Student.Grade> grades;

    // 2. 아예 static 내부 클래스로도 가능.
    static class Grade{
        private String className;
        private String point;
    }
}

// 1. Grade 객체도 만들어줘야하는데, student 와 종속관계니까 내부 클래스로 만들어버려도 됨.
//@Data
//class Grade{
//    private String className;
//    private String point;
//}
