package com.beyond.basic.servletjsp;


import com.beyond.basic.domain.Hello;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// @Controller 가 아닌 WebServlet 을 통해 라우팅.
// 메서드 단위가 아닌 클래스 단위.

@WebServlet("/hello/servlet/rest/get")
public class HelloServletRestGet extends HttpServlet {
    // 매개변수, 리턴 타입을 맞춰주기 위해 가져옴.
    @Override
    // HttpServletRequest 에는 사용자의 요청 정보가 담겨있음.
    // HttpServletResponse 에는 사용자에게 응답 정보를 담아줘야 함.
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Hello hello = new Hello();
        hello.setName("tteia");
        hello.setEmail("test@test.com");
        hello.setPassword("12341234");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        //json 직렬화. controller 에서는 자동으로 해줬음.
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(hello);

        PrintWriter printWriter = resp.getWriter();
        printWriter.print(json);
        printWriter.flush();
    }
}
