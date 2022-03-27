package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/categories/list")
    public ModelAndView showListCategory() {
        ModelAndView modelAndView = new ModelAndView("category/list");
        Iterable<Category> categories = categoryService.findAll();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/categories/create")
    public ModelAndView showCreateCategory() {
        ModelAndView modelAndView = new ModelAndView("category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/categories/create")
    public ModelAndView createCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return new ModelAndView("redirect:/categories/list");
    }


    @GetMapping("/categories/delete/{id}")
    public ModelAndView showDeleteCategory(@PathVariable Long id) {
        Optional<Category> categories = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("category/delete");
        modelAndView.addObject("category", categories.get());
        return modelAndView;
    }

    @PostMapping("/categories/delete/{id}")
    public ModelAndView deleteCategory(@PathVariable Long id) {
        Optional<Category> categories = categoryService.findById(id);
        if (!categories.isPresent()) {
            return new ModelAndView("error-404");
        }
        categoryService.removeById(id);
        return new ModelAndView("redirect:/categories/list");
    }

    @GetMapping("/categories/edit/{id}")
    public ModelAndView ShowEditCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("category/edit");
        modelAndView.addObject("category", categoryOptional.get());
        return modelAndView;
    }

    @PostMapping("/categories/edit/{id}")
    public ModelAndView editCategory(@PathVariable Long id,@ModelAttribute Category category){
        Optional<Category> categories = categoryService.findById(id);
        if (!categories.isPresent()){
            return new ModelAndView("error-404");
        }
        Category oldCategory = categories.get();
        oldCategory.setName(category.getName());
        categoryService.save(oldCategory);
        return new ModelAndView("redirect:/categories/list");
    }


}
