package com.codegym.service;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1,"oto",3000,"4 banh","https://cdnimg.vietnamplus.vn/t1200/Uploaded/qrndqxjwp/2021_10_12/k3-a13d.jpg"));
        products.add(new Product(2,"xe may",2000,"2 banh","https://bloganchoi.com/wp-content/uploads/2019/12/xe-moi-ra-1.jpg"));
        products.add(new Product(3,"xe dap",2000,"2 banh","https://pdp.edu.vn/wp-content/uploads/2021/05/anh-xe-dap-dep-doc-dao.jpg"));
    }
    public static void save(Product product){
        products.add(product);
    }

    public static int findIndexById(int id){
        for (int i = 0; i <products.size() ; i++) {
            if (products.get(i).getId()== id){
                return i;
            }
        }
        return  -1;
    }

    public static void delete(int id){
        products.remove(id);
    }

    public static  void edit(int id , Product product){
        products.set(id,product);
    }

    public static Product getProduct(int id){
        return products.get(findIndexById(id));
    }
}
