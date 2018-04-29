package com.yukoon.policy_cal.controllers;

import com.yukoon.policy_cal.entities.MPolicy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CalController {

    @GetMapping("/cal")
    public String toCal() {
        return "calculator";
    }

    @PostMapping("/single")
    public String calSingle(HttpServletRequest request) {
        //策略数
        int plc_num = Integer.parseInt(request.getParameter("plc_num"));
        //规格
        int spec = Integer.parseInt(request.getParameter("spec"));
        //价格
        float price = Float.parseFloat(request.getParameter("price"));
        //最小
        int min = Integer.parseInt(request.getParameter("min"));
        //最大
        int max = Integer.parseInt(request.getParameter("max"));
        System.out.println(plc_num);
        System.out.println(spec);
        System.out.println(price);
        System.out.println(min);
        System.out.println(max);
        //获取策略
        for (int i=1;i<=plc_num;i++){
            int MorP = Integer.parseInt(request.getParameter("MorP"+i));
            //MorP为0，减钱
            if (MorP == 0){
                MPolicy mPolicy = new MPolicy();
                mPolicy.setId(i);
                mPolicy.setpBox(Integer.parseInt(request.getParameter("p_box"+i)));
                mPolicy.setMoney(Float.parseFloat(request.getParameter("money"+i)));
                System.out.println(mPolicy.toString());
            }
            //MorP为1，赠货
            if (MorP == 1) {

            }
        }
        return "redirect:/cal";
    }
}
