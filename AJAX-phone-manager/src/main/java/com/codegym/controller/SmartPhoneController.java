package com.codegym.controller;


import com.codegym.model.SmartPhone;
import com.codegym.service.ISmartPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/smartphones")
public class SmartPhoneController {

    @Autowired
    private ISmartPhoneService smartPhoneService;

    @GetMapping
    public ResponseEntity<Iterable<SmartPhone>> findAllSmartPhone(){
        return new ResponseEntity<>(smartPhoneService.findAll(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<SmartPhone> createSmartPhone(@RequestBody SmartPhone smartPhone){
        return new ResponseEntity<>(smartPhoneService.save(smartPhone),HttpStatus.CREATED);
    }


    @GetMapping("/list")
    public ModelAndView getAllSmartPhonePage(){
        ModelAndView modelAndView = new ModelAndView("/phones/list");
        modelAndView.addObject("smartphones",smartPhoneService.findAll());
        return modelAndView;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<SmartPhone> deleteSmartPhone(@PathVariable Long id){
        Optional<SmartPhone> smartPhoneOptional = smartPhoneService.findById(id);
        if (!smartPhoneOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        smartPhoneService.remove(id);
        return new ResponseEntity<>(smartPhoneOptional.get(),HttpStatus.OK);
    }


}
