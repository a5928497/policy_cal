package com.yukoon.policy_cal.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PPolicy extends  Policy{
    private int box;
    private int bottle;
}
