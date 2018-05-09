package com.yukoon.policy_cal.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Page implements java.io.Serializable {
    private boolean success;
    private int total;
    private List<Goods> datas;
}
