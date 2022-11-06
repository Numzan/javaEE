package edu.whu.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsItemID implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableField("goodsItemID")
    private Long goodsItemId;

    @TableField("supplierItemID")
    private Long suppliersId;


}