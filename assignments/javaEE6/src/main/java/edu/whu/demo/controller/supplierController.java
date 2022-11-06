package edu.whu.demo.controller;

import edu.whu.demo.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/supplier")
public class supplierController {
    @Autowired
    SupplierServiceImpl supplierService;

    @PostMapping("")
    public supplierController addSupplier(@RequestBody supplierController supplier){

        if(supplierService.addSupplier(supplier)!=null){
            return supplier;
        }else{
            return null;
        }
    }

}

