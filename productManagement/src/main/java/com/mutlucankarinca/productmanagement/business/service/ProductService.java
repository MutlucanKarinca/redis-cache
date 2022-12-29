package com.mutlucankarinca.productmanagement.business.service;

import com.mutlucankarinca.productmanagement.entity.Product;
import com.mutlucankarinca.productmanagement.middleware.models.ExpirationDate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product> getAll();
    Product create(Product product);
    Product getById(Long id);
    Product update(Product product);
    Object deleteById(Long id);
    List<Product> getByName(String name);
    Optional<List<Product>> getByExpirationDate(ExpirationDate date);
    List<Product> getByStatusTrue();
}
