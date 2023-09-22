package com.green.Shop.buy.vo;

import lombok.Data;

import java.util.List;

@Data
public class BuyVO {
    private String buyCode;
    private String memberId;
    private int buyTotalPrice;
    private String buyDate;
    private List<BuyDetailVO> buyDetailList;
}
