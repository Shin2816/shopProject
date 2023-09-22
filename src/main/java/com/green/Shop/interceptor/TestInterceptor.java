package com.green.Shop.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//인터셉터 : 특정 영역(컨트롤러의 메소드 실행 시)에서 반복적으로 실행되야 하는
//          내용을 정의.
//1. 클래스 생성.
//2. HandlerInterceptor 인터페이스를 구현.
//3. HandlerInterceptor 인터페이스 정의된 메소드를 필요에 따라 구현
//구현할 수 있는 메소드 종류
//   1). preHandle() : 컨트롤러의 메소드가 실행되기 전에 검문소 설치.
//   2). postHandle() : 컨트롤러의 메소드가 실행되고, html이 실행되기 전에 검문소 설치.
//   3). afterCompletion() : html이 다 실행된 후 검문소 설치
//4. 구현한 클래스를 언제 실행할지 결정(다른 클래스에서 작성)
@Component
public class TestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle 메소드 실행~");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle 메서드 실행~");
    }
}









