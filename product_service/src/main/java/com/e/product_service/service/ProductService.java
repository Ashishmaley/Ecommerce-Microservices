package com.e.product_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e.product_service.dto.ProductRequest;
import com.e.product_service.dto.ProductResponse;
import com.e.product_service.model.Product;
import com.e.product_service.repository.ProductRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
    
    @Autowired
    private ProductRepo productRepo;

    @SuppressWarnings("null")
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
