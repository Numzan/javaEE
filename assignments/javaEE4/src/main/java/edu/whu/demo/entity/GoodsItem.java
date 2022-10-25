package edu.whu.demo.entity;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@ApiModel(description="商品实体")
public class GoodsItem {
    @ApiModelProperty("商品编号")
    long id;
    @ApiModelProperty("商品名称")
    String name;
    @ApiModelProperty("商品价格")
    double price;

}
