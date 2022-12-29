package com.mutlucankarinca.productmanagement.business.service;

import com.mutlucankarinca.productmanagement.entity.Product;
import com.mutlucankarinca.productmanagement.entity.RedisProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RedisProductService {
    List<RedisProduct> getAll();
    RedisProduct create(RedisProduct redisProduct);
    RedisProduct getById(Long id);
    RedisProduct update(RedisProduct redisProduct);
    Object deleteById(Long id);
    ResponseEntity<RedisProduct> addProduct(RedisProduct redisProduct);
}
