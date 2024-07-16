package com.beyond.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // 주로 웹 서비스 기반의 구성 요소를 스캔하고, 자동으로 등록하는 기능.
// 위 scan 외에도 @WebServlet, @WebFilter, @WebListener 등이 있다.
public class BasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);
	}

}
