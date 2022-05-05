package com.codegym.service.Product;

import com.codegym.model.Product;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService extends IGeneralService<Product> {

    Page<Product> findProductByNameContaining(String name, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    Page<Product> findByCategory(Long id , Pageable pageable);

    Iterable<Product> findProductPriceBetween(Double min, Double max);

}
