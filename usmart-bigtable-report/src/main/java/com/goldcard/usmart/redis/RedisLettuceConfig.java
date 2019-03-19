package com.goldcard.usmart.redis;

import java.time.Duration;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisLettuceConfig {
	
	
	    @Value("${spring.redis.database}")
	    private int database;

	    @Value("${spring.redis.host}")
	    private String host;

	    @Value("${spring.redis.password}")
	    private String password;

	    @Value("${spring.redis.port}")
	    private int port;

	    @Value("${spring.redis.timeout}")
	    private long timeout;

	    @Value("${spring.redis.lettuce.pool.max-idle}")
	    private int maxIdle;

	    @Value("${spring.redis.lettuce.pool.min-idle}")
	    private int minIdle;

	    @Value("${spring.redis.lettuce.pool.max-active}")
	    private int maxActive;

	    @Value("${spring.redis.lettuce.pool.max-wait}")
	    private long maxWait;

	    @Bean
	    LettuceConnectionFactory lettuceConnectionFactory(GenericObjectPoolConfig genericObjectPoolConfig) {
	        // 单机版配置
	        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
	        redisStandaloneConfiguration.setDatabase(database);
	        redisStandaloneConfiguration.setHostName(host);
	        redisStandaloneConfiguration.setPort(port);
	        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));

	        // 集群版配置
//	        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
//	        String[] serverArray = clusterNodes.split(",");
//	        Set<RedisNode> nodes = new HashSet<RedisNode>();
//	        for (String ipPort : serverArray) {
//	            String[] ipAndPort = ipPort.split(":");
//	            nodes.add(new RedisNode(ipAndPort[0].trim(), Integer.valueOf(ipAndPort[1])));
//	        }
//	        redisClusterConfiguration.setPassword(RedisPassword.of(password));
//	        redisClusterConfiguration.setClusterNodes(nodes);
//	        redisClusterConfiguration.setMaxRedirects(maxRedirects);

	        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
	                .commandTimeout(Duration.ofMillis(timeout))
	                .poolConfig(genericObjectPoolConfig)
	                .build();

	        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisStandaloneConfiguration,clientConfig);
	        return factory;
	    }
	    
//	    @Bean
//	    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
//	        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
//	        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
//	        //todo 定制化配置
//	        return stringRedisTemplate;
//	    }
	 
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		@Bean
	    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
	        RedisTemplate redisTemplate = new RedisTemplate();
	        redisTemplate.setConnectionFactory(redisConnectionFactory);

	        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
	        // 设置值（value）的序列化采用FastJsonRedisSerializer。
	        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//	        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
	        // 设置键（key）的序列化采用StringRedisSerializer。
	        redisTemplate.setKeySerializer(new StringRedisSerializer());
	        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

	        
	        
	        return redisTemplate;

	    }

	    /**
	     * GenericObjectPoolConfig 连接池配置
	     *
	     * @return
	     */
	    @Bean
	    public GenericObjectPoolConfig genericObjectPoolConfig() {
	        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
	        genericObjectPoolConfig.setMaxIdle(maxIdle);
	        genericObjectPoolConfig.setMinIdle(minIdle);
	        genericObjectPoolConfig.setMaxTotal(maxActive);
	        genericObjectPoolConfig.setMaxWaitMillis(maxWait);
	        return genericObjectPoolConfig;
	    }


}
