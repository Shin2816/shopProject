package com.green.Shop.admin.service;

import com.green.Shop.admin.vo.MenuVO;
import com.green.Shop.admin.vo.SubMenuVO;
import com.green.Shop.item.vo.CateVO;

import java.util.List;

public interface MenuService {
    //메뉴 목록 조회
    public List<MenuVO> selectMenuList();
    //서브 메뉴 목록 조회
    public List<SubMenuVO> selectSubMenuList(String menuCode);
}
