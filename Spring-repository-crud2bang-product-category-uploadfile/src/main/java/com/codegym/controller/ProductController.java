package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.model.ProductForm;
import com.codegym.service.category.ICategoryService;
import com.codegym.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Value("${file-upload}")
    private String uploadPath;


    @GetMapping("/products/list")
    public ModelAndView showListProduct(@RequestParam(name = "q") Optional<String> q,@RequestParam(name = "t" ) Optional<String> t) {
        ModelAndView modelAndView = new ModelAndView("product/list");
        Iterable<Product> products = productService.findAll();
        if (q.isPresent()){
            products = productService.findProductByNameContaining(q.get());
        }
        if (t.isPresent()){
            products = productService.findByCategory_Name(t.get());
        }
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("products/create")
    public ModelAndView showCreateProduct() {
        ModelAndView modelAndView = new ModelAndView("product/create");
        Iterable<Category> categories = categoryService.findAll();
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("product", new ProductForm());
        return modelAndView;
    }


    @PostMapping("products/create")
    public ModelAndView createProduct(@ModelAttribute ProductForm productForm) {
        String fileName = productForm.getImage().getOriginalFilename();
        long currentTime = System.currentTimeMillis();
        fileName = fileName + currentTime;
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(uploadPath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product product = new Product(productForm.getId(), productForm.getName(), productForm.getPrice(), productForm.getDescription(), fileName);
        product.setCategory(productForm.getCategory());
        productService.save(product);
        return new ModelAndView("redirect:/products/list");

    }


    @GetMapping("/products/delete/{id}")
    public ModelAndView showProductDelete(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ModelAndView("error-404");
        }
        ModelAndView modelAndView = new ModelAndView("product/delete");
        modelAndView.addObject("product", product.get());
        return modelAndView;
    }

    @PostMapping("/products/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ModelAndView("error-404");
        }
        File file = new File(uploadPath + productOptional.get().getImage());
        if (file.exists()) {
            file.delete();
        }
        productService.removeById(id);
        return new ModelAndView("redirect:/products/list");
    }

    @GetMapping("/products/edit/{id}")
    public ModelAndView showEditProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        Iterable<Category>categories = categoryService.findAll();
        if (!productOptional.isPresent()) {
            return new ModelAndView("error-404");
        }
        ModelAndView modelAndView = new ModelAndView("product/edit");
        modelAndView.addObject("product", productOptional.get());
        modelAndView.addObject("categories",categories);
        return modelAndView;
    }

    @PostMapping("/products/edit/{id}")
    public ModelAndView editProduct(@PathVariable Long id, @ModelAttribute ProductForm productForm) {
        MultipartFile img = productForm.getImage();
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()){
            return new ModelAndView("error-404");
        }
        Product oldProduct = productOptional.get();
        if(img.getSize() != 0){
            String fileName = productForm.getImage().getOriginalFilename();
            long currentTime = System.currentTimeMillis();
            fileName = currentTime + fileName;
            oldProduct.setImage(fileName);
            try {
                FileCopyUtils.copy(img.getBytes(),new File(uploadPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            oldProduct.setId(productForm.getId());
            oldProduct.setPrice(productForm.getPrice());
            oldProduct.setDescription(productForm.getDescription());
            oldProduct.setName(productForm.getName());
            oldProduct.setCategory(productForm.getCategory());
            productService.save(oldProduct);
        }
        return new ModelAndView("redirect:/products/list");
    }

    @GetMapping("products/{id}")
    public ModelAndView showProductDetail(@PathVariable Long id){
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()){
            return new ModelAndView("error-404");
        }
        ModelAndView modelAndView = new ModelAndView("product/view");
        modelAndView.addObject("product",productOptional.get());
        return modelAndView;
    }

    @GetMapping("/products/search")
    public ModelAndView showProductSearch(@RequestParam("min") Double min,@RequestParam("max") Double max){
        Iterable<Product> products = productService.findProductPriceBetween(min,max);
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products",products);
        return modelAndView;
    }



}
