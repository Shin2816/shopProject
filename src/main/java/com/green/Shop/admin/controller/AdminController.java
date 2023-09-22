package com.green.Shop.admin.controller;

import com.green.Shop.admin.service.AdminService;
import com.green.Shop.admin.service.MenuService;
import com.green.Shop.admin.vo.SubMenuVO;
import com.green.Shop.item.service.ItemService;
import com.green.Shop.item.vo.ImgVO;
import com.green.Shop.item.vo.ItemSearchVO;
import com.green.Shop.item.vo.ItemVO;
import com.green.Shop.member.vo.MemberVO;
import com.green.Shop.util.ConstantVariable;
import com.green.Shop.util.UploadUtil;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final MenuService menuService;

    //상품 등록 페이지로 이동
    @GetMapping("/regItemForm")
    public String regItemForm(SubMenuVO subMenuVO, Model model){
        model.addAttribute("cateList", adminService.selectCate());
        return "content/admin/reg_item";
    }

    //상품 등록
    @PostMapping("/regItem")
    public String regItem(ItemVO itemVO, MultipartFile mainImg, MultipartFile[] subImg){

        //상품 이미지 등록
        //0. 다음에 들어가야할 item_code를 조회
        String itemCode = adminService.selectNextItemCode(); //현재 있는 code(ex : ItemCode_001)에 숫자를 늘리기 위한 코드 ex에 따르면 현재 값은 ItemCode_002

        //2. 이미지 정보 하나가 들어갈 수 있는 통
        //첨부파일 기능 단일
        ImgVO vo = UploadUtil.uploadFile(mainImg);

        //첨부파일 기능 다중
        List<ImgVO> imgList = UploadUtil.multiFileUpload(subImg);
        imgList.add(vo);
        for(ImgVO imgVO : imgList){
            imgVO.setItemCode(itemCode);
        }

        itemVO.setImgList(imgList); // 저장된 List<ImgVO>를 itemVO에 존재하는 imgList에 저장. - itemVO는 상품 List<ImgVO>는 상품 안에있는 여러가지 이미지.

        //상품 등록 + 이미지 등록 쿼리
        itemVO.setItemCode(itemCode); //itemVO에 현재있는 code에 숫자를 늘리기 위한 쿼리를 돌린 후, code를 현재 상품에 저장
        adminService.insertItem(itemVO); //위의 일련의 과정을 거친(값이 setter로 저장 된) itemVO로 insertItem에 있는 쿼리를 실행

        return "redirect:/admin/regItemForm";
    }

    //상품 목록 페이지로 이동
    @RequestMapping("/itemManage")
    public String itemManage(SubMenuVO subMenuVO, Model model, ItemSearchVO itemSearchVO){

        if(subMenuVO.getSubMenuCode() == null){
            subMenuVO.setSubMenuCode("1");
        }

        model.addAttribute("cateList", adminService.selectCate());
        model.addAttribute("itemList", adminService.selectItemList(itemSearchVO));
        return "content/admin/item_manage";
    }

    //상품 수량 변경
    @PostMapping("/updateStock")
    public String updateStock(ItemVO itemVO){
        adminService.updateStock(itemVO);
        return "redirect:/admin/itemManage";
    }

    //상품 상태 변경
    @ResponseBody
    @PostMapping("/updateStatusFetch")
    public void updateStatus(ItemVO itemVO){
        adminService.updateStatus(itemVO);
    }

    //상단 메뉴 중 고객관리 메뉴를 클릭 시
    //회원 정보 수정 페이지로 이동
    @GetMapping("/memberManage")
    public String memberManage(SubMenuVO subMenuVO){
        if(subMenuVO.getSubMenuCode() == null){
            subMenuVO.setSubMenuCode("4");
        }

        return "/content/admin/update_member_form";
    }

    //회원 목록 페이지
    @GetMapping("selectMemberList")
    public String selectMemberList(SubMenuVO subMenuVO){

        return "/content/admin/member_list";
    }
    
    //연도별 매출 관리 페이지
    @GetMapping("/saleManagePerYear")
    public String saleManagePerYear(SubMenuVO subMenuVO){
        if(subMenuVO.getSubMenuCode() == null){
            subMenuVO.setSubMenuCode("7");
        }
        return "/content/admin/sale_manage_per_year";
    }
}
