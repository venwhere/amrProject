package com.huang.microboot.config;
import com.huang.microboot.util.RedisObjectSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;
@Configuration
public class RedisTwoConfig {
	
	public RedisConnectionFactory getRedisConnectionFactory(String hostName,String password,
			int port,int maxActive,int maxIdle,int minIdle,long maxWait,int database) {
		JedisConnectionFactory jedisConnectionFactory=new JedisConnectionFactory();
		jedisConnectionFactory.setHostName(hostName);
		jedisConnectionFactory.setPort(port);
		jedisConnectionFactory.setPassword(password);
		jedisConnectionFactory.setDatabase(database);
		JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(maxActive);
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMinIdle(minIdle);
		jedisPoolConfig.setMaxWaitMillis(maxWait);
		jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
		jedisConnectionFactory.afterPropertiesSet();
		return jedisConnectionFactory;
	}
	@Bean("redisTwo")
	public RedisTemplate<String,Object> getTemplate(
			@Value("${spring.redis-two.host}")String hostName,
			@Value("${spring.redis-two.password}")String password,
			@Value("${spring.redis-two.port}")int port,
			@Value("${spring.redis-two.pool.max-active}")int maxActive,
			@Value("${spring.redis-two.pool.max-idle}")int maxIdle,
			@Value("${spring.redis-two.pool.min-idle}")int minIdle,
			@Value("${spring.redis-two.pool.max-wait}")long maxWait,
			@Value("${spring.redis-two.database}")int database){
		RedisConnectionFactory redisConnectionFactory=this.getRedisConnectionFactory(hostName,
				password, port, maxActive, maxIdle, minIdle, maxWait, database);
		RedisTemplate<String,Object> template=new RedisTemplate<String,Object>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template;
	}
	
}



















