package com.springboot.restfulwebservice.controller;

import com.springboot.restfulwebservice.exception.ProductNotFoundException;
import com.springboot.restfulwebservice.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductServiceController {

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

    @GetMapping("/products")
    public ResponseEntity<Object> getProduct(){
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        productRepo.put(product.getId(), product);
        return new ResponseEntity<>("Product is created successfully ", HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        if(!productRepo.containsKey(id)) throw new ProductNotFoundException();
        productRepo.remove(id);

        product.setId(id);
        productRepo.put(product.getId(), product);

        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id){
        productRepo.remove(id);

        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }

}
