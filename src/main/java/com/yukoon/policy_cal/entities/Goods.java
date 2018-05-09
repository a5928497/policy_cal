package com.yukoon.policy_cal.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Goods implements java.io.Serializable {
    private String id;
    private String spell;
    private String units;
    private int spec;
    private BigDecimal sellingPrice;
}
