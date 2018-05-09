package com.yukoon.policy_cal.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private String good_name;
    private int spec;
    private float price;
    private int min;
    private int max;
    private List<Policy> list;

}
