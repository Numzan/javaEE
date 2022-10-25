package edu.whu.demo.service;

import edu.whu.demo.entity.GoodsItem;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class GoodsService {
    private Map<Long, GoodsItem> goods =
            Collections.synchronizedMap(new HashMap<Long, GoodsItem>());

    public GoodsItem addGood(GoodsItem good) {
        goods.put(good.getId(), good);
        return good;
    }

    public GoodsItem getGood(long id) {
        return goods.get(id);
    }

    public List<GoodsItem> findGood(String name) {
        List<GoodsItem> result=new ArrayList<>();
        for (GoodsItem good: goods.values()){
            if (name!=null && !good.getName().contains(name)) {
                continue;
            }
            result.add(good);
        }
        return result;
    }

    public void updateGood(long id, GoodsItem good) {
        GoodsItem good2  = goods.get(id);
        good2.setName(good.getName());
        goods.put(id, good2);
    }

    public void deleteGood(long id) {
        goods.remove(id);
    }


}

