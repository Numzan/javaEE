package edu.whu.demo.controller;


import edu.whu.demo.entity.GoodsItem;
import edu.whu.demo.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
@Api
@RestController
@RequestMapping("goods")
public class goodsController {
    @Autowired
    GoodsService goodsService;
    
    // get: localhost:8088/goods/1
    @ApiOperation("根据Id查询")
    @GetMapping("/{id}")
    public ResponseEntity<GoodsItem> getGood(@ApiParam("商品Id")@PathVariable long id){
        GoodsItem result = goodsService.getGood(id);
        if(result==null) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(result);
        }
    }

    // get: localhost:8088/todos
    // get: localhost:8088/todos?name=作业
    // get: localhost:8088/todos?name=作业&&complete=true
    @ApiOperation("根据条件查询待办商品")
    @GetMapping("")
    public ResponseEntity<List<GoodsItem>> findGood(@ApiParam("待办事项名称")String name,@ApiParam double price){
        List<GoodsItem> result = goodsService.findGood(name);
        return ResponseEntity.ok(result);
    }

    @ApiOperation("添加待办商品")
    @PostMapping("")
    public ResponseEntity<GoodsItem> addGood(@RequestBody GoodsItem good){
        GoodsItem result = goodsService.addGood(good);
        return ResponseEntity.ok(result);
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
