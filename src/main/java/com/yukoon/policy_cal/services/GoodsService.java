package com.yukoon.policy_cal.services;

import com.yukoon.policy_cal.entities.Goods;
import com.yukoon.policy_cal.entities.Page;
import com.yukoon.policy_cal.utils.GoodsUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsService {

    public List<Goods> getGoods(){
        Page page = GoodsUtil.getData();
        List<Goods> goodlist = new ArrayList<>();
        for (Goods goods : page.getDatas()) {
            //商品名去重
            if (goods.getSpec() != 1) {
                goodlist.add(goods);
            }
        }
        return goodlist;
    }
}
