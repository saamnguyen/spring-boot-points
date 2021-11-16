package com.springboot.restfulwebservice.controller;

import com.springboot.restfulwebservice.exception.ProductNotFoundException;
import com.springboot.restfulwebservice.model.Product;
import com.springboot.restfulwebservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductServiceController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<Object> getProduct(){
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        productService.createProduct(product);
        return new ResponseEntity<>("Product is created successfully ", HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        productService.updateProduct(id, product);

        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id){
        productService.deleteProduct(id);

        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }

}
