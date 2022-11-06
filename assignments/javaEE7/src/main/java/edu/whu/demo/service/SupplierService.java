package edu.whu.demo.service;

import edu.whu.demo.entity.Supplier;
import edu.whu.demo.dao.supplierDao;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.SelectKey;
import java.util.List;
public class SupplierService extends ServiceImpl<supplierDao, Supplier>  {
    @SelectKey(statement = "select last_insert_id()",keyProperty = "id",keyColumn = "id",resultType = Long.class,before = true)
    public Supplier addSupplier(Supplier supplier){
        if(findSupplier(supplier.getName()).isEmpty()){
            getBaseMapper().insert(supplier);
            return supplier;
        }
        else{
            return null;
        }
    }
    public List<Supplier> findSupplier(String name){
        LambdaQueryWrapper<Supplier> suppliers = new LambdaQueryWrapper<>();
        suppliers.like(Supplier::getName,name);
        List<Supplier> resultList = getBaseMapper().selectList(suppliers);
        return resultList;
    }

    public String findName(long id){
        Supplier supplier = getBaseMapper().selectById(id);
        return supplier.getName();
    }




}