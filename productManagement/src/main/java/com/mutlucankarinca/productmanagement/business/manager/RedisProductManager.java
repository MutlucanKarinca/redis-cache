package com.mutlucankarinca.productmanagement.business.manager;

import com.mutlucankarinca.productmanagement.business.service.RedisProductService;
import com.mutlucankarinca.productmanagement.entity.RedisProduct;
import com.mutlucankarinca.productmanagement.repository.RedisProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisProductManager implements RedisProductService {

    public static final String HASH_KEY = "Product";
    //@Autowired
  //  private RedisTemplate template;
    private final RedisProductRepository redisProductRepository;

    //@Autowired
   // private CacheManager cacheManager;               // autowire cache manager
    @Autowired
    private RedisCacheManager redisCacheManager;

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void clearCache() {
        redisCacheManager.getCacheNames().stream()
                .forEach(cacheName -> redisCacheManager.getCache(cacheName).clear());
    }
    // execure after every 1 hour
    //public void clearCacheSchedule(){
    //    for(String name:cacheManager.getCacheNames()){
    //cacheManager.getCache(name).clear();            // clear cache by name
    //    }
    //}

    @Override
    public List<RedisProduct> getAll() {
        return redisProductRepository.findAll();
    }

    @Override
    public RedisProduct create(RedisProduct redisProduct) {
        return redisProductRepository.save(redisProduct);
    }

    @Override
    public RedisProduct getById(Long id) {
        return redisProductRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Product not found"));
    }

    @Override
    public RedisProduct update(RedisProduct redisProduct) {
        RedisProduct product1 = getById(redisProduct.getId());
        product1.setId(redisProduct.getId());
        product1.setProductName(redisProduct.getProductName());
        product1.setCurrency(redisProduct.getCurrency());
        product1.setStatus(redisProduct.getStatus());
        product1.setUnitPrice(redisProduct.getUnitPrice());
        product1.setExpirationDate(redisProduct.getExpirationDate());
        return redisProductRepository.save(product1);
    }

    @Override
    public Object deleteById(Long id) {
        redisProductRepository.deleteById(id);
        return null;
    }


    @Override
    public ResponseEntity<RedisProduct> addProduct(RedisProduct redisProduct) {
        if (redisProduct.getProductName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        redisProductRepository.save(redisProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(redisProduct);
    }

   // public RedisProduct save(RedisProduct redisProduct){
     //// return redisProduct;
    //}

    //public List<RedisProduct> findAll(){
    //    return template.opsForHash().values(HASH_KEY);
   // }
}
