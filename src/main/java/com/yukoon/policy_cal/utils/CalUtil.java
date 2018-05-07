package com.yukoon.policy_cal.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class CalUtil {

    //求能否整除
    public boolean canDivided(int greater,int smaller){
        boolean flag = (greater % smaller  == 0);
                return flag;
    }

    //求最小公倍数
    public int min_common(int a,int b)
    {
        return (a*b)/max_common(a,b);
    }
    //求最大公约数函数
    public int max_common(int a,int b)
    {
        int max=0;
        if(a<b)
        {   //交换a、b的值
            a=a+b;
            b=a-b;
            a=a-b;
        }
        if(a%b==0)
        {
            max = b;
        }
        while(a % b>0)
        {
            a=a%b;
            if(a<b)
            {
                a=a+b;
                b=a-b;
                a=a-b;
            }
            if(a%b==0)
            {
                max = b;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        CalUtil calUtil = new CalUtil();
        System.out.println(calUtil.canDivided(6,7));
        System.out.println((120%1440));
        System.out.println((int)Math.ceil(3.3));
        System.out.println(Math.floor(3/4));
        System.out.println(calUtil.min_common(24,4));
    }
}
