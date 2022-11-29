package edu.whu.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type =IdType.ASSIGN_ID )
    private Long id;

    private String name;

    private String type;

    private Integer number;

    private Double price;
    @TableField(exist = false)
    private List<Supplier> suppliers;

    public Object getName(GoodsItem goodsItem) {
        return name;
    }
}