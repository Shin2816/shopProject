package com.green.Shop.cart.service;

import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.cart.vo.CartVO;
import com.green.Shop.item.vo.ItemVO;

import java.util.List;

public interface CartService {
    //장바구니 추가
    public int insertCart(CartVO cartVO);
    //장바구니 목록
    public List<CartVO> selectCartList(String memberId);
    //장바구니 삭제
    public int deleteCart(CartVO cartVO);
    //장바구니 수량 수정
    public int updateCart(CartVO cartVO);
    //구매를 위한 장바구니 목록 조회
    public List<BuyDetailVO> selectCartListForBuy(CartVO cartVO);
}
