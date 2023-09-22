package com.green.Shop.item.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class ImgVO {
    private String imgCode;
    private String originFileName;
    private String attachedFileName;
    private String itemCode;
    private String isMain;
}
