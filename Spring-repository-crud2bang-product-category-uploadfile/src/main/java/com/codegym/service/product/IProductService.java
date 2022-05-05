package com.codegym.service.product;

import com.codegym.model.Product;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;

public interface IProductService extends IGeneralService <Product> {

    Page<Product> findProductByNameContaining(String name,Integer page);


    Iterable<Product> findProductPriceBetween(Double min, Double max);

    Page<Product> findByCategory_Name(String nam,Integer page);

    Page<Product> findAll(Integer page);
}
