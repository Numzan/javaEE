package edu.whu.demo.entity;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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
    //@ApiModelProperty("供应商")
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    private List<Supplier> suppliers = new ArrayList<>();
}
