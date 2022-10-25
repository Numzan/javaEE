package edu.whu.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import edu.whu.entity.GoodsItem;
import edu.whu.entity.Supplier;

import java.util.List;

public interface supplierRepository extends JpaRepository<Supplier,Long> {
    @Modifying
    @Query(value = "INSERT INTO good_suppliers(good_id,suppliers_id) VALUES (?1,?2)", nativeQuery = true)
    public void addGoodSupplier(long goodId, long supplierId);

    @Modifying
    @Query(value = "DELETE FROM good_suppliers WHERE id = (?1)",nativeQuery = true)
    public void delGoodSupplier(long id);

    List<Supplier> findByName(String name);
}