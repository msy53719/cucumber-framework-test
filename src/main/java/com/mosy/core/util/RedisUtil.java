package com.mosy.core.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

	public static Jedis getJedis() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(30);
		config.setMaxIdle(10);
		JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379);
		Jedis jedis = jedisPool.getResource();
		return jedis;
	}
}