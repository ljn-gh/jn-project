package com.jianan.demomodule.config;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.Objects;

/**
 * @Author: jn
 * @Date: 2024/11/4
 * @description
 **/
@Configuration
public class CacheConfig {
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(){
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);
        cacheManagerFactoryBean.afterPropertiesSet();
        return new EhCacheCacheManager(Objects.requireNonNull(cacheManagerFactoryBean.getObject()));
    }
}
