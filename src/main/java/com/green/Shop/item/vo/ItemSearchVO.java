package com.green.Shop.item.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class ItemSearchVO {
    private String searchCateCode;
    private String searchItemName;
    private int[] searchItemStatus;
    private String searchItemStockMin;
    private String searchItemStockMax;
}
