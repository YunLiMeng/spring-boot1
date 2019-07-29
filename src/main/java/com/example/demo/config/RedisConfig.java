package com.example.demo.config;

import java.lang.reflect.Method;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redis配置工具类
 * @Configuration表示当前类属于配置类
 * @EnableCaching表示支持缓存
 * @author DELL
 *
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    
	/**
	 * redis key生成策略
	 * target: 类
	 * method: 方法
	 * params: 参数
	 * @return KeyGenerator
	 * 
	 * 注意: 该方法只是声明了key的生成策略,还未被使用,需在@Cacheable注解中指定keyGenerator
	 *      如: @Cacheable(value = "key", keyGenerator = "cacheKeyGenerator")
	 */
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {

			@Override
			public Object generate(Object target, Method method, Object... params) {
				// TODO Auto-generated method stub
				System.out.println("redis key生成策略start~");
				StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                	// 由于参数可能不同, hashCode肯定不一样, 缓存的key也需要不一样
                    sb.append(obj.toString());
                }
                // zhe是我的测试git
                return sb.toString();
			}
        };
    }
}