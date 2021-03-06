package com.codegym.service.Product;

import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }


    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findByCategory(Long id, Pageable pageable) {
        return productRepository.findByCategory(id,pageable);
    }


    @Override
    public Optional <Product> findById(Long id) {
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
    public Page<Product> findProductByNameContaining(String name,Pageable pageable) {
        return productRepository.findByNameContaining(name,pageable);
    }


    @Override
    public Iterable<Product> findProductPriceBetween(Double min, Double max) {
        return productRepository.findProductPriceBetWeen(min,max);
    }
}
