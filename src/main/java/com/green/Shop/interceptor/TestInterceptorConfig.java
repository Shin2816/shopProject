package com.green.Shop.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//구현한 인터셉터 클래스를 언제 실행할지 설정하는 클래스.
//1. WebMvcConfigurer 인터페이스 구현
//2. 해당 인터페이스에서 정의된 addInterceptors() 메소드 구현
@Configuration //객체 생성 어노테이션 + 해당 클래스가 설정 내용이 있는 클래스임을 인지.
@RequiredArgsConstructor
public class TestInterceptorConfig implements WebMvcConfigurer {
    private final TestInterceptor testInterceptor;

    //우리가 만든 인터셉터가 언제 실행할지 결정하는 내용을 작성
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // /item/*  -> /item/aaa O, /item/bbb O, /item/aaa/bbb X
        // /item/**  -> /item/aaa O, /item/bbb O, /item/aaa/bbb O
        registry.addInterceptor(testInterceptor)
                .addPathPatterns("/item/**") // /item으로 시작하는 모든 요청에 대해서~
                .excludePathPatterns("/item/itemDetail");
//                .addPathPatterns("/item/main")
//                .addPathPatterns("/item/itemDetail");


    }


}
