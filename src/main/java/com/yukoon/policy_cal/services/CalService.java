package com.yukoon.policy_cal.services;

import com.yukoon.policy_cal.entities.*;
import com.yukoon.policy_cal.utils.CalUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalService {
    public List<Result> singleProduct(Product product){
        CalUtil calUtil = new CalUtil();
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
                result.setStrategy1(strategy);
                result.setStrategy2("---本策略为金额减免策略--");
            }
            //判断是否赠货
            if(policy instanceof PPolicy){
                //获取bottle
                int bottle = ((PPolicy) policy).getBottle();
                //获取box
                int box = ((PPolicy) policy).getBox();
                //生成策略
                String strategy1 = null;
                String strategy2 = null;
                if (bottle == 0){
                    //整箱赠送，公式=满X箱赠送Y箱
                    strategy1 = "Floor(数量 / "+ (spec*pBox) + " ) * " + ((PPolicy) policy).getBox();
                    strategy2 = "不需要设置单支策略";
                }else {
                    //非整箱赠送，公式=满X箱赠送Y箱+Z支
                    boolean flag = calUtil.canDivided(spec,bottle);
                    if (flag){
                        //若赠送支数能被规格整除
                        /*
                        参数说明：
                        times1:对于赠送支数来说，套餐重复多少次会变成整箱
                        amount：将赠送箱数转换为支数
                        std：套餐可以转换为整箱的最少支数
                        box_num：套餐重复取整后得到的赠送箱数
                        repeat_times = 数量%std/con_amount
                        strategy1:赠送整箱规格的公式
                        strategy2:赠送单支规格的公式
                         */
                        int times  = calUtil.min_common(spec,bottle)/bottle;
                        int amount = spec * pBox;
                        int std = times * amount;
                        int box_num  = (box * times) + (bottle*times)/spec;
                        System.out.println(times);System.out.println(amount);System.out.println(std);System.out.println(times);System.out.println(box_num);
                        //BOX = Floor(数量%std)==0?Floor(数量%std)*box_num:box+Floor(数量%std)*box_num
                        strategy1 = "Floor( 数量 %" + std + ")==0?Floor(数量 % " + std + ")*" + box_num  +
                        ":" + box + "+Floor(数量%" + std + ") *"  + box_num;
                        //Bottle =Floor(数量%std)==0?0:bottle * Floor((数量%std)/amount)
                        strategy2 = "Floor( 数量 % " +std + ")==0 ? 0 :" + bottle + "* Floor((数量 %" + std +
                        " ) /" + amount + ")";
                    }
                }
                result.setStrategy1(strategy1);
                result.setStrategy2(strategy2);
            }
            final_result.add(result);
        }
        return final_result;
    }
}
