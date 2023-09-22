package com.green.Shop.cart.service;

import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.cart.vo.CartVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final SqlSessionTemplate sqlTemplate;

    //장바구니 추가
    @Override
    public int insertCart(CartVO cartVO) {
        return sqlTemplate.insert("cartMapper.insertCart", cartVO);
    }

    //장바구니 목록
    @Override
    public List<CartVO> selectCartList(String memberId) {
        return sqlTemplate.selectList("cartMapper.selectCartList", memberId);
    }

    //장바구니 삭제
    @Override
    public int deleteCart(CartVO cartVO) {
        return sqlTemplate.delete("cartMapper.deleteCart", cartVO);
    }

    @Override
    public int updateCart(CartVO cartVO) {
        return sqlTemplate.update("cartMapper.updateCartCnt", cartVO);
    }

    @Override
    public List<BuyDetailVO> selectCartListForBuy(CartVO cartVO) {
        return sqlTemplate.selectList("cartMapper.selectCartListForBuy", cartVO);
    }


}
