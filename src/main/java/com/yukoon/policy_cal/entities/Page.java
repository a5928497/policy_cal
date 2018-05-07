package com.yukoon.policy_cal.entities;

import java.util.List;

public class Page implements java.io.Serializable {
    private boolean success;
    private int total;
    private List<Goods> datas;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Goods> getDatas() {
        return datas;
    }

    public void setDatas(List<Goods> datas) {
        this.datas = datas;
    }
}
