package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @GetMapping ("calculator")
    public String calculator(ModelMap modelMap ,
                             @RequestParam(defaultValue = "0") double number1 ,
                             @RequestParam(defaultValue ="0") double number2 ,
                             @RequestParam(defaultValue = "") String caLcuL)
    {

        double result = 0;
        switch (caLcuL){
            case "cong":
                result = number1 + number2;
                caLcuL = "cong";
                break;
            case "tru":
                result = number1 - number2;
                caLcuL = "tru";
                break;
            case "nhan":
                result = number1 * number2;
                caLcuL = "nhan";
                break;
            case "chia":
                result = number1 /number2;
                caLcuL = "chia";
                break;

        }

        modelMap.addAttribute("number1",number1);
        modelMap.addAttribute("number2",number2);
        modelMap.addAttribute("calcul",caLcuL);
        modelMap.addAttribute("result",result);
        return "index";
    }
}
