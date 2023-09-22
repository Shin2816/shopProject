package com.green.Shop.cart.vo;

import com.green.Shop.item.vo.ItemVO;
import lombok.*;

import java.util.List;

@Data
//@Data - setter, getter, toString, RequriedArgsConsructor 등등 한번에 생성하는 어노테이션
//@NoArgsConstructor 매개변수가 없는 기본 생성자 생성.
//@RequiredArgsConstructor final 이 붙은 멤버변수를 매개변수로 가진 생성자를 생성.
public class CartVO {
    private String cartCode;
    private String itemCode;
    private String memberId;
    private int cartCnt;
    private String putDate;
    private ItemVO itemList;
    private String[] cartCodes;
}
