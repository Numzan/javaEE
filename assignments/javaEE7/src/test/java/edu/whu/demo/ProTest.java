package edu.whu.demo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.annotation.Rollback;
import edu.whu.demo.service.SupplierService;
import edu.whu.demo.entity.GoodsItem;
import edu.whu.demo.entity.Supplier;
import edu.whu.demo.entity.GoodsItemID;
import edu.whu.demo.dao.goodsDao;
import edu.whu.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
@SpringBootTest
@Transactional
public class ProTest {
    @Autowired
    GoodsItemServiceImpl GoodsService;
    @Autowired
    GoodsItemSuppliersServiceImpl BothService;
    @Autowired
    SupplierServiceImpl SupplierService;
    @Test
    public void addTest() {
        GoodsItem item = new GoodsItem();
        item.setName("apple");
        item.setType("q");
        item.setNumber(2);
        item.setPrice(9999.0);
        Supplier supplier = new Supplier();
        supplier.setName("华为");
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(supplier);
        item.setSuppliers(suppliers);
    }
    @Test
    public void deleteTest() {
        Goods_item item = new Goods_item();
        item.setName("apple");
        item.setType("q");
        item.setNumber(2);
        item.setPrice(9999.0);
        Supplier supplier = new Supplier();
        supplier.setName("华为");
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(supplier);
        try{
            GoodsService.deleteById(edu.whu.demo.service.GoodsService.findById("apple"));
        }catch (ProductAdminException e){
            assertEquals(e.getMessage(),"查找结果为空");
        }
    }
    @Test
    public void testFind(){
        IPage result = goodsItemService.findPageByType();
        System.out.println(result);
    }

    @Test
    public void test11(){
        List<GoodsItem> result = edu.whu.demo.service.GoodsService.findById()
        assertEquals(2,result.size());
    }

}