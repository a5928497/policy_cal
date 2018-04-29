package com.yukoon.policy_cal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalController {

    @GetMapping("/cal")
    public String toCal() {
        return "calculator";
    }
}
