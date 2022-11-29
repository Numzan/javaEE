package edu.whu.demo.controller;


import edu.whu.demo.entity.GoodsItem;
import edu.whu.demo.entity.Supplier;
import edu.whu.demo.service.GoodsService;
import edu.whu.demo.service.SupplierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@Api
@RestController
@RequestMapping("goods")
public class goodsController {
    @Autowired
    GoodsService goodsService;
    
    // get: localhost:8088/goods/1
    @ApiOperation("根据Id查询")
    @GetMapping("/{id}")
    public GoodsItem findById(@PathVariable long id){
        Object Service = null;
        List<Supplier> suppliers = Service.findByGoodsId(id);
        GoodsItem goods = GoodsService.findById(id);

        for(Supplier su : suppliers){
            su.setName(SupplierService.findName(su.getId()));
        }
        goods.setSuppliers(suppliers);
        return goods;
    }

    // get: localhost:8088/todos
    // get: localhost:8088/todos?name=作业
    // get: localhost:8088/todos?name=作业&&complete=true
    @ApiOperation("添加待办商品")
    @PostMapping("")
    public GoodsItem addGoodsItem(@RequestBody GoodsItem goodsItem){
        GoodsItem result = goodsService.addGoodsItem(goodsItem);
        if(result!=null){
            //获取生产者ID
            for(Supplier su:goodsItem.getSuppliers()){
                su.setId(SupplierService.findSupplierByName(su.getName()).get(0).getId());
            }
            Service.addNew(goodsItem);
            return result;
        }
        else{
            return null;
        }
    }

    @ApiOperation("修改待办商品")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGood(@PathVariable long id,@RequestBody GoodsItem good){
        goodsService.updateGood(id,good);
        return ResponseEntity.ok().build();
    }

    @ApiOperation("删除待办事项")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGood(@PathVariable long id){
        goodsService.deleteGood(id);
        return ResponseEntity.ok().build();
    }

}
