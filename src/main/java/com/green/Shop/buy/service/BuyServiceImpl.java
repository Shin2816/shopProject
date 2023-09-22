package com.green.Shop.buy.service;

import com.green.Shop.buy.vo.BuyDetailVO;
import com.green.Shop.buy.vo.BuyVO;
import com.green.Shop.cart.vo.CartVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyServiceImpl implements BuyService{

    private final SqlSessionTemplate sqlTemplate;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBuy(BuyVO buyVO, CartVO cartVO) {
        sqlTemplate.insert("buyMapper.insertBuy", buyVO);
        sqlTemplate.insert("buyMapper.insertBuyDetail", buyVO);
        sqlTemplate.delete("cartMapper.deleteCart", cartVO);
    }
    @Override
    public String selectNextByCode() {
        return sqlTemplate.selectOne("buyMapper.selectNextByCode");
    }

    @Override
    public List<BuyVO> selectBuyList(String memberId) {
        return sqlTemplate.selectList("buyMapper.selectBuyList", memberId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void directBuy(BuyDetailVO buyDetailVO, BuyVO buyVO) {
        sqlTemplate.insert("buyMapper.insertBuy", buyVO);
        sqlTemplate.insert("buyMapper.directBuyDetail", buyDetailVO);
    }


}
