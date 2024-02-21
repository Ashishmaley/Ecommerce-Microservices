package com.e.website.controller;

import org.springframework.web.bind.annotation.RestController;

import com.e.website.dto.ProductRequest;
import com.e.website.dto.ProductResponse;
import com.e.website.service.ProductService;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/product")
public class ProductController {
    
    @Autowired
    private ProductService productService; 

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProduct() {
        return productService.getProducts();
    }
    
}
