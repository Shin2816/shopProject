package com.green.Shop.test;

import com.green.Shop.item.vo.ItemVO;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//html, js에서 넘어오는 데이터를 받는 방식
@Controller
@RequestMapping("/test1")
@RequiredArgsConstructor
public class TestController1 {

    //의존성 주입을 통한 객체 생성 첫번째 방식
    //필드를 통한 의존성 주입
    //@Resource(name="test")
    //private TestService testService;

    //2. 생성자를 통한 의존성 주입
    //@RequiredArgsConstructor는 final이 붙은 멤버변수를 매개변수로 쓰는 생성자를 생성.
    private final TestService testService;

    //생성자를 이용하여서 객체 생성하는것은 어노테이션이 없어도 실행이 가능하나,
    //TestService.java에 @service 어노테이션으로 객체를 생성을 하나 해줘야 사용이 가능.
//    @Autowired
//    public TestController1(TestService testService) {
//        this.testService = testService;
//    }

    @GetMapping("/test1")
    public String test1(){
        testService.test();
        return "/test/test1/test1";
    }

    //매개변수 앞에 @RequestParam 가 생략되어있음.
    //@RequestParam은 메서드가 많은데, name 메서드는 받아오는 변수의 이름을 설정 가능.
    //@RequestParam의 required 메서드는 @RequestParam 을 명시 하지 않았을 경우 false, 명시 했을 경우 true
    //required는 true면 매개변수가 무조건 존재 해야 하는 것.
    //값이 넘어오지 않았을 경우, default값으로 값이 적혀 있는 것을 원하면 defaultValue를 사용.
    //VO 클래스를 매개변수로 들고 올 때, @ModelAttribute를 사용.
    @PostMapping("/test2")
    public String test2(@RequestParam(name = "name1") String name
                        , @RequestParam int age
                        , @RequestParam(required = false, defaultValue = "java") String name2
                        , String name3
                        , ItemVO itemVO){
        System.out.println(name);
        System.out.println(name2);
        System.out.println(age);
        return "/test/test1/test1";
    }

}
