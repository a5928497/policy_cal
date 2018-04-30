package com.yukoon.policy_cal.services;

import com.yukoon.policy_cal.entities.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalService {
    public List<Result> singleProduct(Product product){
        List<Policy> list = product.getList();
        List<Result> final_result = new ArrayList<>();
        for (int i = 0;i<list.size();i++){
            Policy policy = list.get(i);
            //满X字段
            int pBox = policy.getpBox();;
            //规格字段
            int spec = product.getSpec();
            //价格字段
            float price = product.getPrice();
            //最小范围字段
            int min = product.getMin();
            //最大范围字段
            int max = product.getMax();
            Result result = new Result();
            result.setName("策略"+(i+1));
            //判断条件
            String condition;
            if (max == 0) {
                //无上限时
                condition  = "价格 >= " + price + " && 数量 >= " + min;
            }else {
                //有上限时
                condition  = "价格 >= " + price + " && 数量 >= " + min +" && 数量 < " + max;
            }
            result.setCondition(condition);
            //判断是否减钱
            if(policy instanceof MPolicy) {
                //生成策略
                String strategy;
                if(pBox != 1){
                    strategy = "Floor(数量 / (" + spec + " * "+ pBox +" ) * " +  ((MPolicy) policy).getMoney();
                }else {
                    strategy = "Floor(数量 / " + spec + " ) * " +  ((MPolicy) policy).getMoney();
                }
                result.setStrategy(strategy);
                final_result.add(result);
            }
            //判断是否赠货
            if(policy instanceof PPolicy){
                //获取bottle
                int bottle = ((PPolicy) policy).getBottle();
                //生成策略
                String strategy;
                if (bottle == 0){
                    //整箱赠送，公式=满X箱赠送Y箱
                    strategy = "Flooro(数量 / "+ product;
                }else {
                    //非整箱赠送
                }

            }
        }
        return final_result;
    }
}
