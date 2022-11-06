package edu.whu.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.whu.demo.dao.goodsDao;
import edu.whu.demo.entity.GoodsItem;
import org.apache.ibatis.annotations.SelectKey;
public class GoodsService extends ServiceImpl<goodsDao, GoodsItem> {
    @SelectKey(statement = "select last_insert_id()",keyProperty = "id",keyColumn = "id",resultType = Long.class,before = true)
    public GoodsItem addGoodsItem(GoodsItem GoodsItem){
        LambdaQueryWrapper<GoodsItem> lqw = new LambdaQueryWrapper<>();
        lqw.like(GoodsItem::getName,GoodsItem.getName());
        if(getBaseMapper().selectList(lqw).size()==0){
            getBaseMapper().insert(GoodsItem);
        }
        else{
            return null;
        }
        return GoodsItem;
    }


    public static GoodsItem findById(long id){
        return getBaseMapper().selectById(id);
    }

    public GoodsItem updateGood(long id,GoodsItem goods){

        deleteById(id);
        if(addGoodsItem(goods)!=null){
            return goods;
        }else{
            return null;
        }
    }

    public void deleteById(long id){
        int i = getBaseMapper().deleteById(id);

        System.out.println(i);
    }

    public int typeCount(String type){
        LambdaQueryWrapper<GoodsItem> lqw = new LambdaQueryWrapper<>();
        lqw.like(GoodsItem::getType,type);
        return getBaseMapper().selectCount(lqw);
    }

    public GoodsItem findByName(String name){
        LambdaQueryWrapper<GoodsItem> lqw = new LambdaQueryWrapper<>();
        lqw.like(GoodsItem::getName,name);
        GoodsItem result= new GoodsItem();
        return result;
    }

}