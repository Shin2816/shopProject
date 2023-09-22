package com.green.Shop.buy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.Shop.buy.service.BuyService;
import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.buy.vo.BuyVO;
import com.green.Shop.cart.service.CartService;
import com.green.Shop.cart.vo.CartVO;
import com.green.Shop.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/buy")
@RequiredArgsConstructor
public class BuyController {

    private final BuyService buyService;
    private final CartService cartService;

    //장바구니 품목 구매
    @GetMapping("/insertBuy")
    public String insertBuy(CartVO cartVO, BuyVO buyVO, HttpSession session){
        //가져온 cartCodeList를 사용해서 ITEM_CODE, BUY_CNT, BUY_PRICE 를 구함.
        List<BuyDetailVO> buyDetailList = cartService.selectCartListForBuy(cartVO);


        //구매 총 가격
        int buyTotalPrice = 0;
        for(BuyDetailVO buyDetail : buyDetailList){
            buyTotalPrice += buyDetail.getBuyPrice();
        }
        //구매자 ID 구하기
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        //구매 정보 넣기
        buyVO.setMemberId(loginInfo.getMemberId());
        buyVO.setBuyCode(buyService.selectNextByCode());
        buyVO.setBuyTotalPrice(buyTotalPrice);
        buyVO.setBuyDetailList(buyDetailList);

        //쿼리 실행
        buyService.insertBuy(buyVO, cartVO);
        return "redirect:/cart/list";
    }

    @ResponseBody
    @PostMapping("/insertBuyFetch")
    public void insertBuyFetch(@RequestBody Map<String , Object> data, HttpSession session){

        BuyVO buyVO = new BuyVO();


        String buyCode = buyService.selectNextByCode();
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        buyVO.setBuyCode(buyCode);
        buyVO.setMemberId(loginInfo.getMemberId());
        buyVO.setBuyTotalPrice((Integer)data.get("buyTotalPrice"));

        ObjectMapper mapper = new ObjectMapper();
        BuyDetailVO[] detailArr = mapper.convertValue(data.get("detailList"),BuyDetailVO[].class);
        List<BuyDetailVO> buyDetailList = Arrays.asList(detailArr);

        for(BuyDetailVO vo : buyDetailList){
            vo.setBuyCode(buyCode);
            vo.setBuyDetailCode(buyCode);
        }

        buyVO.setBuyDetailList(buyDetailList);


        CartVO cartVO = new CartVO();
        String[] cartCodes = mapper.convertValue(data.get("cartCodes"), String[].class);
        cartVO.setCartCodes(cartCodes);

        buyService.insertBuy(buyVO, cartVO);
    }

    //바로 구매하기
    @PostMapping("/directBuy")
    public String directBuy(BuyDetailVO buyDetailVO, BuyVO buyVO, HttpSession session){
        //다음에 들어갈 buyCode 값 조회
        String buyCode = buyService.selectNextByCode();
        buyDetailVO.setBuyCode(buyCode);

        buyVO.setBuyCode(buyCode);
        buyVO.setMemberId(((MemberVO)session.getAttribute("loginInfo")).getMemberId());
        buyVO.setBuyTotalPrice(buyDetailVO.getBuyPrice());

        buyService.directBuy(buyDetailVO, buyVO);
        return "redirect:/buy/list";
    }

    @GetMapping("/list")
    public String buyList(CartVO cartVO, Model model, HttpSession session){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");

        List<BuyVO> list = buyService.selectBuyList("abc");
        System.out.println(list);
        //목록 조회
        model.addAttribute("buyList", buyService.selectBuyList(loginInfo.getMemberId()));
        return "/content/buy/buy_list";
    }

}
