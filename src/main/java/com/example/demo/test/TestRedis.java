package com.example.demo.test;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.entity.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestRedis {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void test() throws Exception {
		System.out.println("999999999");
		stringRedisTemplate.opsForValue().set("aaa", "111");
		System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
	}
	
	@Test
    public void testObj() throws Exception {
		Student user=new Student(1L, "aa", "aa123456","123");
        ValueOperations<String, Student> operations=redisTemplate.opsForValue();
        operations.set("com.neox", user);
        operations.set("com.neo.f", user,1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");
        boolean exists=redisTemplate.hasKey("com.neo.f");
        if(exists){ 
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
       // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }
}
