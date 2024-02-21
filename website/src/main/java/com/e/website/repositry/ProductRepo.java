package com.e.website.repositry;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.e.website.model.Product;

public interface ProductRepo extends MongoRepository<Product, String> {
}
