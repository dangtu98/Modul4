package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.service.CategoryService.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryRestController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Category>> findAllCategory(Pageable pageable){
        Page<Category> categories = (Page<Category>) categoryService.findAll(pageable);
        if (categories.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryOptional.get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.save(category),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> editCategory(@RequestBody Category category , @PathVariable Long id){
        Optional<Category> category1 = categoryService.findById(id);
        if (!category1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        category.setId(category1.get().getId());
        category.setName(category1.get().getName());
        return new ResponseEntity<>(categoryService.save(category),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id){
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        categoryService.remove(id);
        return new ResponseEntity<>(categoryOptional.get(),HttpStatus.OK);
    }





}
