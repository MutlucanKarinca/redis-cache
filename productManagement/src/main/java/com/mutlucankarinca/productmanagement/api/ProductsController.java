package com.mutlucankarinca.productmanagement.api;

import com.mutlucankarinca.productmanagement.business.service.ProductService;
import com.mutlucankarinca.productmanagement.entity.Product;
import com.mutlucankarinca.productmanagement.middleware.HelperMethods;
import com.mutlucankarinca.productmanagement.middleware.models.ErrorMessage;
import com.mutlucankarinca.productmanagement.middleware.models.ExpirationDate;
import jakarta.persistence.Cacheable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Object> getAll(){
        try {
            return HelperMethods.generateResponse("Success", HttpStatus.OK, productService.getAll());
        }catch (Exception e){
            return HelperMethods.generateResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        try {
            return HelperMethods.generateResponse("Success", HttpStatus.OK, productService.getById(id));
        }catch (Exception e){
            return HelperMethods.generateResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Object> getByName(@PathVariable String name){
        try {
            List<Product> productList = productService.getByName(name);
            if(productList.size() > 0){
                return HelperMethods.generateResponse("Success", HttpStatus.OK, productList);
            }else {
                return HelperMethods.generateResponse("No Content", HttpStatus.NO_CONTENT,null);
            }
        }catch (Exception e){
            return HelperMethods.generateResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }

    @GetMapping("/searchByDate")
    public ResponseEntity<Object> searchByDate(@Valid @RequestBody ExpirationDate expireDate){
        Optional<List<Product>> products = productService.getByExpirationDate(expireDate);
    if(products.isPresent()){
        return HelperMethods.generateResponse("Success", HttpStatus.OK, products);
    }
        return HelperMethods.generateResponse("No Content", HttpStatus.NO_CONTENT,null);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Product product){
        try {
            return HelperMethods.generateResponse("Success", HttpStatus.OK, productService.create(product));
        }catch (Exception e){
            return HelperMethods.generateResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }
    @GetMapping("/statusTrue")
    public ResponseEntity<Object> getByStatusTrue(){
        try {
            return HelperMethods.generateResponse("Success", HttpStatus.OK, productService.getByStatusTrue());
        }catch (Exception e){
            return HelperMethods.generateResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@Valid @RequestBody Product product){
        try {
            return HelperMethods.generateResponse("Success", HttpStatus.OK, productService.update(product));
        }catch (Exception e){
            return HelperMethods.generateResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED, product);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        try {
            return HelperMethods.generateResponse("Success", HttpStatus.OK, productService.deleteById(id));
        }catch (Exception e){
            return HelperMethods.generateResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }
}
