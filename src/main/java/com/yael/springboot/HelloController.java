package com.yael.springboot;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @GetMapping 어노테이션을 사용하면 어플리케이션 실행 시
 * 어노테이션에 작성된 "/hello"와 같은 경로 정보들을 테이블에 한꺼번에 저장해뒀다가
 * 요청이 들어오면 테이블에서 해당 경로와 매핑된 빈을 찾아서 생성해주는 것.
 * @RestController를 붙이면 @ResponseBody 가 자동으로 붙기때문에 메소드마다 @ResponseBody 를 붙여주지 않아도 된다.
 * 스프링은 이러한 관례들이 많기때문에 관례를 잘 알고, 스프링이 어떤식으로 코드를 붙여줄 지 머릿속에 떠올라야 한다.서
 */
@RestController
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
