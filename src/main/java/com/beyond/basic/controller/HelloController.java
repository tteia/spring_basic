package com.beyond.basic.controller;

import com.beyond.basic.domain.Hello;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

// 1. 이건 컨트롤러야! 표시해주기.
@Controller
@RequestMapping("/hello") // 클래스 차원에 url 매핑 시에 RequestMapping 사용
//@RestController // Controller + 각 메서드마다 @ResponseBody
// 해당 클래스가 컨트롤러(사용자의 요청을 처리하고 응답하는 편의기능)임을 명시.
public class HelloController {

//     case1. 사용자가 서버에게 화면 요청
//     case2. @ResponseBody 가 붙을 경우 단순 String 데이터 return(get 요청)

    // 2. GetMapping 기능 사용하겠다 선언.
    // GetMapping 을 통해 get 요청을 처리하고 url 패턴을 명시.
    @GetMapping("/")
    // 3. ResponseBody 선언.
    // ResponseBody 를 사용하면 화면이 아닌 데이터를 return 해준다.
    // 만약 ResponseBody 가 없다면 ?
    // 스프링은 templates 폴더 밑에 helloWorld.html 화면을 찾아 return 해준다.
    // >> 약속된 패턴 ! 화면을 리턴하려면 ResponseBody 를 써 줄 필요가 없다.
    // 그러려면 templates 아래 html 파일이 존재해야 함. 없으면 에러!
//    @ResponseBody

    public String helloWorld(){
        return "helloworld";
    }
    // ===== 4. 상단까지 작성 후 실행 > localhost:8080/hello > hello world 출력

//    case3. 사용자가 json 데이터 요청(get)
    // data 를 return 하되, json 형식으로 return.
    // method 명은 helloJson, url 패턴은 /hello/json
    @GetMapping("/json")
//    @RequestMapping // 메서드 차원에서도 RequestMappong 사용 가능
    @ResponseBody
    // ResponseBody 를 사용하면서 객체를 return 시 자동으로 직렬화.
    public Hello helloJson() throws JsonProcessingException {
        Hello hello = new Hello();
        hello.setName("hello");
        hello.setEmail("test@test.com");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String value = objectMapper.writeValueAsString(hello);
//        return value;
        return hello;
    }

    // case4. 사용자가 json 데이터를 요청하되, parameter 형식으로 특정 객체 요청함.
    // get 요청 중에 특정 데이터를 요청
    @GetMapping("/param1")
    @ResponseBody
    // method 명 : Param1
    // parameter 형식 : ?name=tteia&email=test@test.com
    // & 가 들어가면 2개 이상을 뜻함.
    // 요청 > localhost:8080/hello/param1?name=tteia
    public Hello Param1(@RequestParam(value = "name") String inputName){
        // DB 데이터가 있다고 가정(Hello 에), 조회
        Hello hello = new Hello();
        hello.setName(inputName);
        hello.setEmail("test@test.com");
        System.out.println(hello);
        return hello;
    }

    // url 패턴 : param2, method 명 : Param2
    // parameter 2 개 : name, email => hello 객체 생성 후 리턴
    // 요청 방식 : ?name=xxx&email=xxx@test.com
    @GetMapping("/param2")
    @ResponseBody
    public Hello Param2(@RequestParam(value = "name") String inputName, @RequestParam(value = "email") String inputEmail){
        // DB 데이터가 있다고 가정(Hello 에), 조회
        Hello hello = new Hello();
        hello.setName(inputName);
        hello.setEmail(inputEmail);
        return hello;
    }

    // case5. parameter 형식으로 요청하되, 서버에서 데이터 바인딩하는 형식.
    @GetMapping("/param3")
    @ResponseBody
    // parameter 가 많을 경우(이름, 이메일, 비밀번호, 주소...) 객체로 대체가 가능하다.
    // 객체의 각 변수에 맞게 알아서 바인딩(객체 바인딩) 해줌.
    // Hello 객체로 가서 password 부터 추가해주기 !
    // ?name=xxx&email=xxx@test.com&password=xxxx
    // 데이터 바인딩 : 기본 생성자, setter 존재해야함.
    // Hello hello 로 가져오니까 변수명이 다르면 안됨. > setName 에서는 inputName 해줬지만 이 경우에는 불가.
    public Hello Param3(Hello hello){
        return hello;
    }

    // case6. 서버에서 화면에 데이터를 넣어 사용자에게 return(model 객체 사용)
    // SSR
    // RestController = Controller + ResponseBody (데이터)
    // case6 에서는 화면을 return 할 것이기 때문에 RestController 이나 ResponseBody 써줄 수 없다 !
    // **님, hello world 를 return 하려고 하는 것 !
    @GetMapping("/model-param")
    public String modelParam(@RequestParam(value = "name")String inputName, Model model){
        // model 객체에 name 이라는 키값의 value 를 세팅하면 해당 key:value 는 화면으로 전달됨.
        // helloworld.html 로 가서 추가 작성.
        // model 을 통해 데이터를 화면에 주입했다.
        model.addAttribute("name", inputName); // 변수명 name > html 파일에서도 동일하게 name 으로 써줌. (name1이면 name1)
        return "helloworld";
    }

    // case7. pathVariable 방식을 통해 사용자로부터 값을 받아 화면 return.
    // localhost:8080/hello/model-path/1 (1번 사용자 데이터 줘 ! id 값이 되는 것)
    // localhost:8080/hello/model-path/tteia
    // pathVariable 방식은 url 을 통해 자원의 구조를 명확하게 표현하므로 좀 더 restful 한 형식이다.
    // restful = html + java
    @GetMapping("/model-path/{inputName}") // /뒤에 name 이 들어올거야!
    public String modelPath(@PathVariable String inputName, Model model){
        model.addAttribute("name",inputName);
        return "helloworld";
    }

    // post 요청 (사용자 입장에서 서버에 데이터를 주는 상황)
    // post 요청 방식 => 두 가지가 있다 !
    // 1) html 로 form 태그를 사용하여 post 요청
    // 1-1) url 인코딩(text 만 전송할 때), 1-2) multipart/form-data
    // 2) js 를 사용하는 요청 방식
    // 2-1) json 2-2)formData 객체 사용(multipart)

    // case1. url 인코딩 방식(text 만 전송)
    // post 요청(사용자 입장에서 서버에 데이터를 주는 상황) -> 형식 : key1=value1&key2=value2
    // orm 태그가 있는 화면을 먼저 줘야한다.
    // 사용자 <- 서버 : 화면을 준다 // get 요청
    // 사용자 -> 서버 : 데이터를 받는다.(post)

    // 우선 get 요청 먼저! > 회원가입 할 정보 주세요 ~
    // 사용자에게 name, email, password 를 요청할 수 있는 화면을 주는 메서드 정의.
    // 메서드명 : formView, 화면명 : form-view
    @GetMapping("/form-view")
    public String formView(){
        return "form-view";
    }

    // post 요청
    @PostMapping("/form-post1") //GetMapping 과 같은 url 패턴 사용도 가능.
    @ResponseBody // 데이터 리턴할거라서 써주기
    public String formPost1(@RequestParam(value = "name") String inputName,
                            @RequestParam(value = "email") String inputEmail,
                            @RequestParam(value = "password") String inputPassword){

        // 사용자로부터 받아온 내용 출력
        System.out.println("입력하신 이름은 : " + inputName);
        System.out.println("이메일은 : " + inputEmail);
        System.out.println("비밀번호는 : " + inputPassword);
        return "ok";
    }

    @PostMapping("/form-post2")
    @ResponseBody
    public String formPost2(Hello hello){ //데이터 바인딩 방식. @ModelAttribute Hello hello 이지만 생략 가능.
        System.out.println(hello); // toString 내장되어있어서 가능함.
        return "ok";
    }

    // case2. multipart/form-data 방식(text와 파일) 전송
    // url명 : form-file-post, 메서드명 : formFilePost, 화면명 : form-file-view
    // form태그 : name, email, password, file
    @GetMapping("/form-file-post")
    public String formFilePost(){
        return "form-file-view";
    }

    @PostMapping("/form-file-post") // 위는 get 여기는 post 라서 같은 url 사용 가능.
    @ResponseBody
    public String formFileHandle(Hello hello,
                                 @RequestParam(value = "image")MultipartFile image){
        System.out.println("hello");
        System.out.println(image.getOriginalFilename());
        return "ok";
    }

    // case3. js를 활용한 form 데이터 전송(text만)
    @GetMapping("/axios-form-view")
    public String axiosFormView(){
        // name, email, password 를 전송 (vscode post 참조)
        return "axios-form-view";
    }

    @PostMapping("axios-form-view")
    @ResponseBody
    public String axiosFormPost(Hello hello){
        System.out.println(hello);
        return "ok";
    }

    //case4. js를 활용한 form 데이터 전송(+file)
    @GetMapping("axios-form-file-view")
    public String axiosFormFileView(){
        return "axios-form-file-view";
    }

    @PostMapping("axios-form-file-view")
    @ResponseBody
    public String axiosFormFileViewPost(Hello hello, @RequestParam(value = "image")MultipartFile image){
        System.out.println(hello);
        System.out.println(image.getOriginalFilename());

        return "ok";
    }


    //case5. js를 활용한 json 데이터 전송
    //case6. js를 활용한 json 데이터 전송(+file)
    //case7. js를 활용한 json 데이터 전송(+여러 file)
}
