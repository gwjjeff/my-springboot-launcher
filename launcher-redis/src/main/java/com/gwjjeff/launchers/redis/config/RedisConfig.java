package com.gwjjeff.launchers.redis.config;

import com.gwjjeff.launchers.redis.bean.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by jeff on 2017/5/4.
 */
@Configuration
@PropertySource("classpath:application-redis.properties")
public class RedisConfig {
    @Bean
    JedisPoolConfig jedisPoolConfig(
            @Value("${redis.pool.maxTotal:40}") int maxTotal,
            @Value("${redis.pool.maxIdle:20}") int maxIdle,
            @Value("${redis.pool.minIdle:2}") int minIdle,
            @Value("${redis.pool.maxWait:1000}") long maxWait
    ) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        return jedisPoolConfig;
    }
    @Bean
    JedisConnectionFactory jedisConnectionFactory(
            JedisPoolConfig poolConfig,
            @Value("${redis.host}") String host,
            @Value("${redis.port}") int port
    ) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setPoolConfig(poolConfig);
        return jedisConnectionFactory;
    }
    @Bean
    public RedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisTemplate userInfoRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, UserInfo> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new RedisObjectSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(UserInfo.class));
        return redisTemplate;
    }
}
