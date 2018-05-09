package com.yukoon.policy_cal.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {
    private String name;
    private  String condition;
    private  String strategy1;
    private String strategy2;
}
