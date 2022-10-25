package edu.whu.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import edu.whu.demo.repository.supplierRepository;
import edu.whu.demo.repository.goodsRepository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierService {
    @Autowired
    supplierRepository repository;

    public Supplier addSupplier(Supplier supplier){
        return repository.saveAndFlush(supplier);

    }


    public Optional<Supplier> getSupplier(long id){
        return repository.findById(id);
    }

    public void updateSupplier(long id,Supplier supplier){
        repository.save(supplier);
    }

    public void deleteSupplier(long id){
        repository.deleteById(id);
    }

    public List<Supplier> findSuppliers(String name) {

        return repository.findByName(name);
    }
}
