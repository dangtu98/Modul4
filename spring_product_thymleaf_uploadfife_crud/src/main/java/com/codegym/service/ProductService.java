package com.codegym.service;

import com.codegym.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    private List<Product> products = new ArrayList<>();

    @Override
    public List<Product> findAll() {
        return products;
    }


    public int findProductById(int id){
        int index = -1;
        for (int i = 0; i < products.size() ; i++){
            if (products.get(i).getId()==id){
                index = i;
            }
        }
        return index;
    }

    @Override
    public Product findById(int id) {
        int index = findProductById(id);
        if (index != -1){
            return  products.get(index);
        }
        return null;
    }

    @Override
    public void create(Product product) {
        products.add(product);
    }

    @Override
    public void delete(int id) {
       int index = findProductById(id);
       products.remove(index);
    }

    @Override
    public void update(int id, Product product) {
       int index = findProductById(id);
       products.set(index,product);
    }

    @Override
    public List<Product> finAllByName(String name) {
        List<Product> products1 = new ArrayList<>();
        for (int i = 0; i < products.size() ; i++) {
            if (products.get(i).getName().contains(name)){
                products1.add(products.get(i));
            }
        }
        return products1;
    }
}
