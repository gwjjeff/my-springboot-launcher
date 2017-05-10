package com.gwjjeff.launchers;

import com.gwjjeff.launchers.redisStarter.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
    private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String, User> userRedisTemplate;

	@Test
	public void testUserOps() throws Exception {

		// 保存字符串
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));

		// 保存对象
		User user = new User("superman", 20);
		userRedisTemplate.opsForValue().set(user.getUsername(), user);

		user = new User("蝙蝠侠", 30);
		userRedisTemplate.opsForValue().set(user.getUsername(), user);

		user = new User("蜘蛛侠", 40);
		userRedisTemplate.opsForValue().set(user.getUsername(), user);

		Assert.assertEquals(20, userRedisTemplate.opsForValue().get("超人").getAge().longValue());
		Assert.assertEquals(30, userRedisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
		Assert.assertEquals(40, userRedisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());

	}

}