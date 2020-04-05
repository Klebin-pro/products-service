package com.microcommerce.product.controller;

import com.microcommerce.product.exception.NotFoundException;
import com.microcommerce.product.model.Product;
import com.microcommerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody Product product) {
        Product p = productService.add(product);
        if (p == null) return ResponseEntity.noContent().build();
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping()
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/sorted/name")
    public List<Product> getAllSortedByName() {
        return productService.getAllOrderByNameAsc();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable int id) {
        Optional<Product> optional = productService.getById(id);
        if (optional.isPresent()) return optional.get();
        throw new NotFoundException(String.format("Not found product with id %s", id));
    }

    @GetMapping("/profits")
    public Map<String, Float> calculateProfit() {
        return productService.calculateProfit();
    }
}

