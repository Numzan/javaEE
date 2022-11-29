package edu.whu.demo.dao;

import edu.whu.demo.entity.GoodsItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface goodsDao extends BaseMapper<GoodsItem> {

}