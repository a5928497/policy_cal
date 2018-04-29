package com.yukoon.policy_cal.entities;

public class Result {
    private String name;
    private  String condition;
    private  String strategy;

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
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
                ", strategy='" + strategy + '\'' +
                '}';
    }
}
