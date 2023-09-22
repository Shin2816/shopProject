package com.green.Shop.buy.service;

import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.buy.vo.BuyVO;
import com.green.Shop.cart.vo.CartVO;

import java.util.List;

public interface BuyService {
    // 상품 구매 등록
    public void insertBuy(BuyVO buyVO, CartVO cartVO);
    // 상품 코드 조회
    public String selectNextByCode();
    // 상품 구매 목록
    public List<BuyVO> selectBuyList(String memberId);
    // 상품 단일 구매
    public void directBuy(BuyDetailVO buyDetailVO, BuyVO buyVO);
}
