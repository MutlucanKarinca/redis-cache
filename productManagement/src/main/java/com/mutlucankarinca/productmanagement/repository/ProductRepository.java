package com.mutlucankarinca.productmanagement.repository;

import com.mutlucankarinca.productmanagement.entity.Product;
import com.mutlucankarinca.productmanagement.middleware.models.ExpirationDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(
            value = "SELECT * FROM PRODUCTS WHERE product_name LIKE %?1%",
            nativeQuery = true)
    Optional<List<Product>> findByName(String name);

    @Query(
    value = "select * from PRODUCTS WHERE expiration_date > :expirationDate",
            nativeQuery = true
    )
    Optional<List<Product>> findProductByExpirationDate(LocalDate expirationDate);

    Optional<List<Product>> findAllByStatusTrue();

}
