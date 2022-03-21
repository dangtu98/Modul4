package com.codegym.controller;

import com.codegym.model.Dictionary;
import com.codegym.service.DictionaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TranslationController {
    private DictionaryService dictionaryService = new DictionaryService();
    @GetMapping("search")
    public String search(){
        return "search";
    }

    @PostMapping("translation")
    public String result(@RequestParam String keyword , Model model){
        List<Dictionary> dictionaries = dictionaryService.findAll();
        for (int i = 0; i < dictionaries.size() ; i++) {
            if (keyword.equals(dictionaries.get(i).getEn())){
                model.addAttribute("word",dictionaries.get(i).getVi());
                model.addAttribute("key",keyword);
            }
        }
         return "translation";
    }


}
