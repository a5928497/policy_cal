package com.yukoon.policy_cal.entities;

public class Policy {
    private int id;
    private int pBox;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpBox() {
        return pBox;
    }

    public void setpBox(int pBox) {
        this.pBox = pBox;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "id=" + id +
                ", pBox=" + pBox +
                '}';
    }
}
