package com.e.product_service.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.e.product_service.model.Product;


public interface ProductRepo extends MongoRepository<Product, String> {
}
