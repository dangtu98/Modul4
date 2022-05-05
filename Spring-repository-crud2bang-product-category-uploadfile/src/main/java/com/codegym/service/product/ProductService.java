package com.codegym.service.product;

import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ProductService implements IProductService {


    @Autowired
    private IProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return  productRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Integer page) {
        return productRepository.findAll(PageRequest.of(page,5));
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void removeById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findProductByNameContaining(String name,Integer Page) {
        return productRepository.findByNameContaining(name,PageRequest.of(Page,5 ));
    }


    @Override
    public Iterable<Product> findProductPriceBetween(Double min, Double max) {
        return productRepository.findProductPriceBetween(min, max);
    }

    @Override
    public Page<Product> findByCategory_Name(String name,Integer page) {
        return productRepository.findByCategory_Name(name,PageRequest.of(page,5));
    }


}
