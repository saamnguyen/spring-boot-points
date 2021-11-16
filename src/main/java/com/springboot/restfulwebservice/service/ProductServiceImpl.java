package com.springboot.restfulwebservice.service;

import com.springboot.restfulwebservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{
    private static Map<String, Product> productRepo = new HashMap<>();

    static {
        Product product = new Product();

        product.setId("1");
        product.setName("Iphone");

        productRepo.put(product.getId(), product);

        Product product1 = new Product();

        product1.setId("2");
        product1.setName("Samsung");

        productRepo.put(product1.getId(), product1);
    }

    @Override
    public void createProduct(Product product) {
        productRepo.put(product.getId(), product);
    }

    @Override
    public void updateProduct(String id, Product product) {
        productRepo.remove(id);

        product.setId(id);
        productRepo.put(product.getId(), product);
    }

    @Override
    public void deleteProduct(String id) {
        productRepo.remove(id);
    }

    @Override
    public Collection<Product> getProducts() {
        return productRepo.values();
    }
}
