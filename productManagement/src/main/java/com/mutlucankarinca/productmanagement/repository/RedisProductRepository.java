package com.mutlucankarinca.productmanagement.repository;

import com.mutlucankarinca.productmanagement.entity.Product;
import com.mutlucankarinca.productmanagement.entity.RedisProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisProductRepository extends JpaRepository<RedisProduct, Long> {

}
