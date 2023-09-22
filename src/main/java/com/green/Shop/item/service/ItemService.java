package com.green.Shop.item.service;

import com.green.Shop.item.vo.CateVO;
import com.green.Shop.item.vo.ImgVO;
import com.green.Shop.item.vo.ItemVO;

import java.util.List;

public interface ItemService {

    public List<ItemVO> selectMemberItemList(ItemVO itemVO);
    public ItemVO selectItemDetail(String itemCode);

}
