package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.ProductForm;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Value("${file-upload}")
    private String uploadPath;


    @GetMapping("")
    public ModelAndView showProductList(@RequestParam(required = false) String q ){
        ModelAndView modelAndView = new ModelAndView("products/list");
        List<Product> products = productService.findAll();
        if (q != null){
           products = productService.finAllByName(q);

        }
        modelAndView.addObject("products",products);
        return modelAndView;
    }


    @GetMapping("/delete/{id}")
    public ModelAndView showProductDelete(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView("/products/delete");
        Product product = productService.findById(id);
        modelAndView.addObject("product",product);
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable Integer id) {
        Product product = productService.findById(id);
        File file = new File(uploadPath + product.getImage());
        if (file.exists()){
            file.delete();
        }
        productService.delete(id);
        return new ModelAndView("redirect:/products");
    }


    @GetMapping("/create")
    public ModelAndView showProductCreate(){
        ModelAndView modelAndView = new ModelAndView("/products/create");
        modelAndView.addObject("product", new ProductForm());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@ModelAttribute ProductForm productForm){
        String fileName = productForm.getImage().getOriginalFilename();
        Long currentTime = System.currentTimeMillis();
        fileName = currentTime + fileName;
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(),new File(uploadPath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product product = new Product(productForm.getId(),productForm.getName(),productForm.getPrice(),productForm.getDescription(),fileName);
        productService.create(product);
        return  new ModelAndView("redirect:/products");

    }

    @GetMapping("/edit/{id}")
    public ModelAndView showProductEdit(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView("/products/edit");
        Product product = productService.findById(id);
        modelAndView.addObject("product",product);
        return modelAndView;
    }


    @PostMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable Integer id, @ModelAttribute ProductForm productForm){
        MultipartFile multipartFile = productForm.getImage();
        if (multipartFile.getSize() == 0){
            Product productIntact = productService.findById(id);
            Product product = new Product(productForm.getId(),productForm.getName(),productForm.getPrice(),productForm.getDescription(),productIntact.getImage());
            productService.update(id,product);
        }else {
            String fileName = productForm.getImage().getOriginalFilename();
            Long currentTime = System.currentTimeMillis();
            fileName = currentTime + fileName;
            try {
                FileCopyUtils.copy(productForm.getImage().getBytes(),new File(uploadPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Product product = new Product(productForm.getId(),productForm.getName(),productForm.getPrice(),productForm.getDescription(),fileName);
            productService.update(id,product);
        }

        return new ModelAndView("redirect:/products");
    }



}
