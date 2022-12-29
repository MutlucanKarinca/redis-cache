package com.mutlucankarinca.productmanagement.business.manager;

import com.mutlucankarinca.productmanagement.business.service.ProductService;
import com.mutlucankarinca.productmanagement.middleware.models.ExpirationDate;
import com.mutlucankarinca.productmanagement.repository.ProductRepository;
import com.mutlucankarinca.productmanagement.entity.Product;
import jakarta.persistence.Cacheable;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    @Override
    public Product update(Product product) {
        Product product1 = getById(product.getId());
        product1.setId(product.getId());
        product1.setProductName(product.getProductName());
        product1.setCurrency(product.getCurrency());
        product1.setStatus(product.getStatus());
        product1.setUnitPrice(product.getUnitPrice());
        product1.setExpirationDate(product.getExpirationDate());
        return productRepository.save(product1);
    }

    @Override
    public Object deleteById(Long id) {
        productRepository.deleteById(id);
        return null;
    }

    @Override
    public List<Product> getByName(String name) {
        return productRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    @Override
    public Optional<List<Product>> getByExpirationDate(ExpirationDate date){
        return productRepository.findProductByExpirationDate(date.getExpirationDate());
    }

    @Override
    public List<Product> getByStatusTrue() {
        return productRepository.findAllByStatusTrue().orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }
}
