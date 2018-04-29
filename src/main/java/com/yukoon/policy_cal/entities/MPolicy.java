package com.yukoon.policy_cal.entities;

public class MPolicy extends Policy{
    private float money;

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return super.toString()+"MPolicy{" +
                "money=" + money +
                '}';
    }
}
