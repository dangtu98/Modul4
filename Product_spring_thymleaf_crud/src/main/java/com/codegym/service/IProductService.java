package com.codegym.service;

import com.codegym.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    Product findById(int id);

    void Create(Product product);

    void DeleteById(int id);

    void update(int id, Product product);





}
