package com.springBootRedis.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
public class ApplicationTests {
//	http://blog.csdn.net/jackie_xiaonan/article/details/70187752
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Test
	public void test() throws Exception{
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
		
	}
	
	
}
