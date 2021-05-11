package com.yaof.config;

/**
 * @Author yaof
 * @Date 2021-04-23
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * 创建redis监听配置类
 */
@Configuration
public class RedisListenerConfig {

//    @Autowired
//    private NoticePipelineListener noticePipelineListener;

    @Bean
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container
                = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);

        return container;
    }


}
