package com.yukoon.policy_cal.entities;

public class Good {
    private int id;
    private int spec;
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpec() {
        return spec;
    }

    public void setSpec(int spec) {
        this.spec = spec;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", spec=" + spec +
                ", price=" + price +
                '}';
    }
}
