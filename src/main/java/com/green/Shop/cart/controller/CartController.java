package com.green.Shop.cart.controller;

import com.green.Shop.cart.service.CartService;
import com.green.Shop.cart.vo.CartVO;
import com.green.Shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    //장바구니에 상품 등록
    @ResponseBody
    @PostMapping("/insertCartFetch")
    public int cartInsert(CartVO cartVO, HttpSession session){
        //memberId값 세팅
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        cartVO.setMemberId(loginInfo.getMemberId());
        return cartService.insertCart(cartVO);
    }

    //장바구니 목록 출력
    @GetMapping("/list")
    public String cartList(HttpSession session, Model model){
        //MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        List<CartVO> list = cartService.selectCartList("abc");
        model.addAttribute("cartList", list);
        return "/content/cart/cart_list";
    }

    //장바구니 목록 삭제
    //deleteCart 의 리턴값을 CartVO로 했기 때문에 cartCodes를 사용하지 않음.
    @GetMapping("/deleteCart")
    public String deleteCart(CartVO cartVO){
        cartService.deleteCart(cartVO);
        return "redirect:/cart/list";
    }

    @ResponseBody
    @PostMapping("/updateCartCntFetch")
    public void updateCartCnt(CartVO cartVO){
        cartService.updateCart(cartVO);
    }

//    @GetMapping("/deleteCart")
//    public String deleteCart(@RequestParam(name="cartCoeds") List<String> cartCodes, CartVO cartVO){
//        cartService.deleteCart(cartCodes);
//        return "redirect:/cart/list";
//    }

}
