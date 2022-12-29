package com.mutlucankarinca.productmanagement.api;

import com.mutlucankarinca.productmanagement.business.service.RedisProductService;
import com.mutlucankarinca.productmanagement.entity.RedisProduct;
import com.mutlucankarinca.productmanagement.middleware.HelperMethods;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping("redisProduct")
public class RedisProductController {

    private final RedisProductService redisProductService;

    private CacheManager cacheManager;               // autowire cache manager
    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    @GetMapping("/clear")// execure after every 1 hour
    public void clearCacheSchedule(){
        for(String name:cacheManager.getCacheNames()){
            cacheManager.getCache(name).clear();            // clear cache by name
        }
    }
    @GetMapping
    public ResponseEntity<Object> getAll(){
        try {
            return HelperMethods.generateResponse("Success", HttpStatus.OK, redisProductService.getAll());
        }catch (Exception e){
            return HelperMethods.generateResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }
    @PostMapping("/add")
    public ResponseEntity<RedisProduct> saveProduct(@RequestBody RedisProduct redisProduct)  {
        return redisProductService.addProduct(redisProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        try {
            return HelperMethods.generateResponse("Success", HttpStatus.OK, redisProductService.getById(id));
        }catch (Exception e){
            return HelperMethods.generateResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody RedisProduct redisProduct){
        try {
            return HelperMethods.generateResponse("Success", HttpStatus.OK, redisProductService.create(redisProduct));
        }catch (Exception e){
            return HelperMethods.generateResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Object> update(@Valid @RequestBody RedisProduct redisProduct){
        try {
            return HelperMethods.generateResponse("Success", HttpStatus.OK, redisProductService.update(redisProduct));
        }catch (Exception e){
            return HelperMethods.generateResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED, redisProduct);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        try {
            return HelperMethods.generateResponse("Success", HttpStatus.OK, redisProductService.deleteById(id));
        }catch (Exception e){
            return HelperMethods.generateResponse(e.getMessage(), HttpStatus.EXPECTATION_FAILED, null);
        }
    }
}
