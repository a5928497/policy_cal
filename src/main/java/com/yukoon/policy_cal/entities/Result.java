package com.yukoon.policy_cal.entities;

public class Result {
    private String name;
    private  String condition;
    private  String strategy1;
    private String strategy2;

    public String getStrategy1() {
        return strategy1;
    }

    public void setStrategy1(String strategy1) {
        this.strategy1 = strategy1;
    }

    public String getStrategy2() {
        return strategy2;
    }

    public void setStrategy2(String strategy2) {
        this.strategy2 = strategy2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Result{" +
                "name='" + name + '\'' +
                ", condition='" + condition + '\'' +
                ", strategy1='" + strategy1 + '\'' +
                ", strategy2='" + strategy2 + '\'' +
                '}';
    }
}
