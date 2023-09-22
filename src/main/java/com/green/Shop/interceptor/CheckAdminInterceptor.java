package com.green.Shop.interceptor;

import com.green.Shop.member.vo.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CheckAdminInterceptor implements HandlerInterceptor {
    //관리자 권한이 필요한 페이지로 이동 시 만약 관리자 권한이 없다면
    //쇼핑몰의 첫 페이지로 이동.

    //return true -> 원래 실행하려던 메소드를 실행.
    //return false -> 원래 실행하려던 메소드를 실행하지 않고 다른 페이지로 이동
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //세션에 저장된 로그인 정보
        HttpSession session = request.getSession();
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        //로그인을 하지 않았거나 관리자가 아니라면
        //쇼핑몰의 첫 페이지로 이동
        if(loginInfo == null || !loginInfo.getMemberRoll().equals("ADMIN")){
            response.sendRedirect("/item/main");
            return false;
        }

        return true;
    }
}
