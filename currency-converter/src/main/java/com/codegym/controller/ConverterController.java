package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ConverterController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping("/converter")
    public String converter(@RequestParam String rate, String usd,Model model) {
        float vnd = Float.parseFloat(rate) * Float.parseFloat(usd);
        model.addAttribute("vnd", vnd);
        return "converter";
    }
}
