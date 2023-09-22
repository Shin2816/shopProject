package com.green.Shop.admin.service;

import com.green.Shop.admin.vo.MenuVO;
import com.green.Shop.admin.vo.SubMenuVO;
import com.green.Shop.item.vo.CateVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService{

    private final SqlSessionTemplate sqlSession;

    @Override
    public List<MenuVO> selectMenuList() {
        return sqlSession.selectList("menuMapper.selectMenuList");
    }

    @Override
    public List<SubMenuVO> selectSubMenuList(String menuCode) {
        return sqlSession.selectList("menuMapper.selectSubMenuList", menuCode);
    }

}
