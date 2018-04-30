package com.yukoon.policy_cal.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class CalUtil {
    private int times;
    //求能否整除
    public boolean canDivided(int greater,int smaller){
//        int bottle;
//        int box;
//        int number;
//        if ((number%bottle)!=0) {
//            bottle = number%bottle;
//            box = box + Math.floor(number/bottle);
//
//        }
        boolean flag = (greater % smaller  == 0);
                return flag;
    }

    public int times(){

        return times;
    }

    public static void main(String[] args) {
        CalUtil calUtil = new CalUtil();
        System.out.println(calUtil.canDivided(2,4));
    }
}
