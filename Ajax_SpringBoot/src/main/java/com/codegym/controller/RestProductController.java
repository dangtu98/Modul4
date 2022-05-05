package com.codegym.controller;


import com.codegym.model.Product;
import com.codegym.model.ProductForm;
import com.codegym.service.Product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class RestProductController {

    @Autowired
    private IProductService productService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(@RequestParam(name = "q") Optional<String> q, @PageableDefault(value = 5) Pageable pageable) {
        Page<Product> products = productService.findAll(pageable);
        if (q.isPresent()) {
            products = productService.findProductByNameContaining(q.get(), pageable);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Product> save(@RequestBody Product product){
//        return new ResponseEntity<>(productService.save(product),HttpStatus.OK);
//    }


    @PostMapping
    public ResponseEntity<Product> save(@ModelAttribute ProductForm productForm) {
        MultipartFile image = productForm.getImage();
        if (image.getSize() != 0) {
            String fileName = productForm.getImage().getOriginalFilename();
            long currentTime = System.currentTimeMillis();
            fileName = currentTime + fileName;
            try {
                FileCopyUtils.copy(productForm.getImage().getBytes(), new File(uploadPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Product product = new Product(productForm.getId(), productForm.getName(), productForm.getPrice(), productForm.getDescription(), fileName, productForm.getCategory());
            return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    @PostMapping("/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product newProduct) {
//        Optional<Product> productOptional = productService.findById(id);
//        if (!productOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        newProduct.setId(id);
//        return new ResponseEntity<>(productService.save(newProduct), HttpStatus.OK);
//    }


    @PostMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @ModelAttribute ProductForm productForm) {
        Optional<Product> oldProduct = productService.findById(id);
        if (!oldProduct.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MultipartFile multipartFile = productForm.getImage();
        String fileName = "";
        if (multipartFile != null) {
            fileName = multipartFile.getOriginalFilename();
            long currentTime = System.currentTimeMillis();
            fileName = currentTime + fileName;
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            fileName = oldProduct.get().getImage();
        }
        Product product = new Product(id, productForm.getName(), productForm.getPrice(), productForm.getDescription(), fileName, productForm.getCategory());
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.removeById(id);
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

//    @GetMapping("/search/{id}")
//    public ResponseEntity<Page<Product>> findByCategory(Long id , @PageableDefault(value = 5) Pageable pageable){
//        Page<Product> products = productService.findByCategory(id,pageable);
//        if (products.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(products,HttpStatus.OK);
//    }

}
