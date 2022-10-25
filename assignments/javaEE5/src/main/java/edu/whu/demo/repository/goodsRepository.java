package edu.whu.demo.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import edu.whu.demo.entity.GoodsItem;


import java.util.List;

public interface goodsRepository extends JpaRepository<GoodsItem,Long>, JpaSpecificationExecutor<GoodsItem> {
    List<GoodsItem> findByName(String name);

    List<GoodsItem> findByNameContaining(String name);

    List<GoodsItem> findByPriceBetween(int price1,int price2);

    Page<GoodsItem> findByNameContaining(String name, Pageable pageable);
}