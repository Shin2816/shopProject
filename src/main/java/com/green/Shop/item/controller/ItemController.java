package com.green.Shop.item.controller;

import com.green.Shop.admin.service.AdminService;
import com.green.Shop.admin.service.MenuService;
import com.green.Shop.item.service.ItemService;
import com.green.Shop.item.vo.ImgVO;
import com.green.Shop.item.vo.ItemVO;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final MenuService menuService;

    //쇼핑몰 메인 페이지
    @GetMapping("/main")
    public String shopMain(ItemVO itemVO, Model model){
        List<ItemVO> itemList = itemService.selectMemberItemList(itemVO);
        model.addAttribute("MemberItemList", itemList);

        //메뉴 목록 조회
        model.addAttribute("menuList", menuService.selectMenuList());

        return "content/item/main";
    }
    @GetMapping("/itemDetail")
    public String itemDetail(String itemCode, Model model){
        ItemVO vo = itemService.selectItemDetail(itemCode);
        model.addAttribute("item", vo);
        return "content/item/item_detail";
    }


}
