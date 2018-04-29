package com.yukoon.policy_cal.entities;

public class PPolicy extends  Policy{
    private int box;
    private int bottle;

    public int getBox() {
        return box;
    }

    public void setBox(int box) {
        this.box = box;
    }

    public int getBottle() {
        return bottle;
    }

    public void setBottle(int bottle) {
        this.bottle = bottle;
    }

    @Override
    public String toString() {
        return super.toString()+"PPolicy{" +
                "box=" + box +
                ", bottle=" + bottle +
                '}';
    }
}
