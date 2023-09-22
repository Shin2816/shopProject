package com.green.Shop.item.service;

import com.green.Shop.item.vo.CateVO;
import com.green.Shop.item.vo.ImgVO;
import com.green.Shop.item.vo.ItemVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final SqlSessionTemplate sqlTemplate;

    @Override
    public List<ItemVO> selectMemberItemList(ItemVO itemVO) {
        return sqlTemplate.selectList("itemMapper.selectMemberItemList", itemVO);
    }

    @Override
    public ItemVO selectItemDetail(String itemCode) {
        return sqlTemplate.selectOne("itemMapper.selectItemDetail", itemCode);
    }

}
