package com.yukoon.policy_cal.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yukoon.policy_cal.entities.Good;
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

    public String getGoodDetail(String id) throws JsonProcessingException {
        Page page = GoodsUtil.getData();
        Good good = new Good();
        //遍历查找对应的商品
        for (Goods goods:page.getDatas()) {
            if (goods.getId().equals(id)){
                good.setId(Integer.parseInt(goods.getId()));
                good.setPrice(Float.parseFloat(goods.getSellingPrice().toString()));
                good.setSpec(goods.getSpec());
            }
        }

        //使用Jackson转JSON
        ObjectMapper mapper = new ObjectMapper();
        String userJSON = mapper.writeValueAsString(good);
        return userJSON;
    }
}
