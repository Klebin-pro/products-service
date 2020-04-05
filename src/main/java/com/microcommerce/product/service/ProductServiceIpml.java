package com.microcommerce.product.service;

import com.microcommerce.product.dao.ProductDAO;
import com.microcommerce.product.exception.NotFoundException;
import com.microcommerce.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceIpml implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public Product add(@Valid Product product) {
        return productDAO.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productDAO.findAll();
    }

    @Override
    public List<Product> getAllOrderByNameAsc() {
        return productDAO.findByOrderByNameAsc();
    }

    @Override
    public Optional<Product> getById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public Map<String, Float> calculateProfit() {
        List<Product> all = getAll();
        if (all == null) throw new NotFoundException("Nothing product found");
        HashMap<String, Float> productProfits = new HashMap<>();
        for (Product p : all) {
            productProfits.put(p.getName(), p.getPrice() - p.getBuyingPrice());
        }
        return productProfits;
    }
}
