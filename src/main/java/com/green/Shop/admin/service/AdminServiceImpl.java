package com.green.Shop.admin.service;

import com.green.Shop.item.vo.CateVO;
import com.green.Shop.item.vo.ItemSearchVO;
import com.green.Shop.item.vo.ItemVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final SqlSessionTemplate sqlTemplate;

    //카테고리 목록 조회
    @Override
    public List<CateVO> selectCate() {
        return sqlTemplate.selectList("adminMapper.selectCate");
    }

    //상품 등록 + 상품 이미지 등록
    //rollbackFor = Exception.class 어떤 오류가 났던기 오류가 출력이 되었을 때, 롤백함.
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertItem(ItemVO itemVO) {
        sqlTemplate.insert("adminMapper.insertItem", itemVO);
        sqlTemplate.insert("adminMapper.insertImgs", itemVO);
    }

    //상품 목록 조회
    @Override
    public List<ItemVO> selectItemList(ItemSearchVO itemSearchVO) {
        return sqlTemplate.selectList("adminMapper.selectItemList", itemSearchVO);
    }

    //상품 수량 변경
    @Override
    public void updateStock(ItemVO itemVO) {
        sqlTemplate.update("adminMapper.updateStock", itemVO);
    }

    //상품 상태 변경
    @Override
    public void updateStatus(ItemVO itemVO) {
        sqlTemplate.update("adminMapper.updateStatus", itemVO);
    }

    @Override
    public String selectNextItemCode() {
        return sqlTemplate.selectOne("adminMapper.selectNextItemCode");
    }


}
