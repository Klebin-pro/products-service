package com.microcommerce.product.service;

import com.microcommerce.product.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {

    @javax.validation.Valid Product add(Product product);

    List<Product> getAll();

    List<Product> getAllOrderByNameAsc();

    Optional<Product> getById(int id);

    Map<String, Float> calculateProfit();
}
