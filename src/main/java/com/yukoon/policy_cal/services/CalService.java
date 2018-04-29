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
        Result result = new Result();
        int pBox;
        for (int i = 0;i<list.size();i++){
            Policy policy = list.get(0);
            pBox = policy.getpBox();
            result.setName("策略"+(i+1));
            //判断赠货还是减钱
            if(policy instanceof MPolicy) {
                //生成判断条件
                String condition;
                //判断条件范围
                if (product.getMax() == 0) {
                    condition  = "价格 >= " + product.getPrice() + " && 数量 >= " + product.getMin();
                }else {
                    condition  = "价格 >= " + product.getPrice() + " && 数量 >= " + product.getMin() +" && 数量 < " +product.getMax();
                }
                result.setCondition(condition);
                //生成策略
                String strategy;
                if(pBox != 1){
                    strategy = "Floor(数量 / (" + product.getSpec() + " * "+ policy.getpBox()+" ) * " +  ((MPolicy) policy).getMoney();
                }else {
                    strategy = "Floor(数量 / " + product.getSpec() + " ) * " +  ((MPolicy) policy).getMoney();
                }
                result.setStrategy(strategy);
                final_result.add(result);
            }
        }
        return final_result;
    }
}
