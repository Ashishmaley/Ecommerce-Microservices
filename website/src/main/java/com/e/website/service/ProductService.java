package com.e.website.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e.website.dto.ProductRequest;
import com.e.website.dto.ProductResponse;
import com.e.website.model.Product;
import com.e.website.repositry.ProductRepo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
    
    @Autowired
    private ProductRepo productRepo;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder().name(productRequest.getName()).desc(productRequest.getDesc())
                .price(productRequest.getPrice()).build();
        product = productRepo.save(product);
        log.info("Product saved {}", product.getId());
    }

    public List<ProductResponse> getProducts() {
        List<Product> product = productRepo.findAll();
        return product.stream().map(p -> ProductResponse.builder().name(p.getName()).desc(p.getDesc())
                .price(p.getPrice()).id(p.getId()).build()).toList();
    }

}
