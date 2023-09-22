package com.green.Shop.interceptor;

import com.green.Shop.admin.service.MenuService;
import com.green.Shop.admin.vo.MenuVO;
import com.green.Shop.admin.vo.SubMenuVO;
import com.green.Shop.item.vo.CateVO;
import com.green.Shop.member.vo.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MenuInterceptor implements HandlerInterceptor {
    private final MenuService menuService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        //model에 담겨서 넘어오는 데이터 받기
        Map<String, Object> data = modelAndView.getModel();
        SubMenuVO subMenuVO = (SubMenuVO)data.get("subMenuVO");

        //메뉴 목록 조회
        List<MenuVO> menuList = menuService.selectMenuList();

        //서브 메뉴 목록 조회
        List<SubMenuVO> subMenuList = menuService.selectSubMenuList(subMenuVO.getMenuCode());


        //조회한 데이터를 html로 전달
        modelAndView.addObject("menuList", menuList);
        modelAndView.addObject("subMenuList", subMenuList);



    }


}
