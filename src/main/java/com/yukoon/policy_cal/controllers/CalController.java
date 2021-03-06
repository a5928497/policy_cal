package com.yukoon.policy_cal.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yukoon.policy_cal.entities.*;
import com.yukoon.policy_cal.services.CalService;
import com.yukoon.policy_cal.services.GoodsService;
import com.yukoon.policy_cal.utils.GoodsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CalController {

    @Autowired
    private CalService calService;
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/cal")
    public String toCal(Map<String,Object> map) {
        List<Goods> goodlist = goodsService.getGoods();
        map.put("goods",goodlist);
        return "calculator";
    }

    @PostMapping("/single")
    public String calSingle(HttpServletRequest request, Product product, Map<String,Object> map) {
        //策略数
        int plc_num = Integer.parseInt(request.getParameter("plc_num"));
        List<Policy> list = new ArrayList<>();
        //获取策略
        for (int i=1;i<=plc_num;i++){
            int MorP = Integer.parseInt(request.getParameter("MorP"+i));
            //MorP为0，减钱
            if (MorP == 0){
                MPolicy mPolicy = new MPolicy();
                mPolicy.setId(i);
                mPolicy.setPBox(Integer.parseInt(request.getParameter("p_box"+i)));
                mPolicy.setMoney(Float.parseFloat(request.getParameter("money"+i)));
                System.out.println(mPolicy.toString());
                list.add(mPolicy);
            }
            //MorP为1，赠货
            if (MorP == 1) {
                PPolicy pPolicy = new PPolicy();
                pPolicy.setId(i);
                pPolicy.setPBox(Integer.parseInt(request.getParameter("p_box"+i)));
                pPolicy.setBox(Integer.parseInt(request.getParameter("box"+i)));
                pPolicy.setBottle(Integer.parseInt(request.getParameter("bottle"+i)));
                System.out.println(pPolicy.toString());
                list.add(pPolicy);
            }
        }
        product.setList(list);
        System.out.println(product);
        map.put("results",calService.singleProduct(product));
        return "result";
    }

    @ResponseBody
    @GetMapping("/goods_detail")
    public String goodsDetail(@RequestParam String id) throws JsonProcessingException {

        return  goodsService.getGoodDetail(id);
    }

    @ResponseBody
    @GetMapping("/goods_json")
    public String goodsJSON(){
        return  goodsService.getGoodsJSON();
    }
}
