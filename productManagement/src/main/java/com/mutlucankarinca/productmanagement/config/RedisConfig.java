package com.mutlucankarinca.productmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
    //edisConnectionFactory connectionFactory;

    //@Bean
    //public RedisTemplate<String, Object> redisTemplate() {
      //  RedisTemplate<String, Object> template = new RedisTemplate<>();
      //  template.setConnectionFactory(connectionFactory);
     //   return template;
  //  }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

   // @Bean
    //public RedisTemplate<?, ?> redisTemplate() {
     //   RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
      //  template.setConnectionFactory(redisConnectionFactory());
       // return template;
    //}

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(60)) // Time  To Leave Config
                .disableCachingNullValues();

    }
}
