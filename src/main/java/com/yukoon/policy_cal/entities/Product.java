package com.yukoon.policy_cal.entities;

import java.util.List;

public class Product {
    private String good_name;
    private int spec;
    private float price;
    private int min;
    private int max;
    private List<Policy> list;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Policy> getList() {
        return list;
    }

    public void setList(List<Policy> list) {
        this.list = list;
    }

    public int getSpec() {
        return spec;
    }

    public void setSpec(int spec) {
        this.spec = spec;
    }



    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getGood_name() {
        return good_name;
    }

    public void setGood_name(String good_name) {
        this.good_name = good_name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "good_name='" + good_name + '\'' +
                ", spec=" + spec +
                ", price=" + price +
                ", min=" + min +
                ", max=" + max +
                ", list=" + list +
                '}';
    }
}
