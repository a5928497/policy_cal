package com.yukoon.policy_cal.controllers;

import com.yukoon.policy_cal.entities.MPolicy;
import com.yukoon.policy_cal.entities.PPolicy;
import com.yukoon.policy_cal.entities.Policy;
import com.yukoon.policy_cal.entities.Product;
import com.yukoon.policy_cal.services.CalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CalController {

    @Autowired
    private CalService calService;

    @GetMapping("/cal")
    public String toCal() {
        return "calculator";
    }

    @ResponseBody
    @PostMapping("/single")
    public String calSingle(HttpServletRequest request, Product product) {
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
                mPolicy.setpBox(Integer.parseInt(request.getParameter("p_box"+i)));
                mPolicy.setMoney(Float.parseFloat(request.getParameter("money"+i)));
                System.out.println(mPolicy.toString());
                list.add(mPolicy);
            }
            //MorP为1，赠货
            if (MorP == 1) {
                PPolicy pPolicy = new PPolicy();
                pPolicy.setId(i);
                pPolicy.setpBox(Integer.parseInt(request.getParameter("p_box"+i)));
                pPolicy.setBox(Integer.parseInt(request.getParameter("box"+i)));
                pPolicy.setBottle(Integer.parseInt(request.getParameter("bottle"+i)));
                System.out.println(pPolicy.toString());
                list.add(pPolicy);
            }
        }
        product.setList(list);
        System.out.println(product);
        return calService.singleProduct(product).toString();
    }
}
