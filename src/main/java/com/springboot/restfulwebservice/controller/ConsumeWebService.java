package com.springboot.restfulwebservice.controller;

import com.springboot.restfulwebservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class ConsumeWebService {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/template/products")
    public String getProductList(){
        HttpHeaders httpHeaders = new HttpHeaders();

        //Yêu cầu trả về định dạng Json
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(
                "http://localhost:8080/products", HttpMethod.GET, entity, String.class
        ).getBody();
    }

    @PostMapping("/template/products")
    public String createProduct(@RequestBody Product product){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<Product>(product, httpHeaders);

        return restTemplate.exchange(
                "http://localhost:8080/products", HttpMethod.POST, entity, String.class
        ).getBody();
    }

    @PutMapping("/template/products/{id}")
    public String updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<Product>(product, httpHeaders);

        return restTemplate.exchange(
                "http://localhost:8080/products"+id, HttpMethod.PUT, entity, String.class
        ).getBody();
    }

    @DeleteMapping("/template/products/{id}")
    public String deleteProduct(@PathVariable("id") String id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<Product>(httpHeaders);

        return restTemplate.exchange(
                "http://localhost:8080/products"+id, HttpMethod.DELETE, entity, String.class
        ).getBody();
    }
}
