package io.pivotal.cf.servicebroker;

import io.pivotal.cf.servicebroker.model.ServiceInstance;
import io.pivotal.cf.servicebroker.model.ServiceInstanceBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.servicebroker.model.BrokerApiVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@Profile("cloud")
public class CloudConfig {

    @Autowired
    private Environment env;

    //TODO move to brokeredservice?
    @Bean
    public BrokerApiVersion brokerApiVersion() {
        return new BrokerApiVersion("2.7");
    }

    //TODO deal with custom env parms
    @Bean
    String serviceUri() {
        return env.getProperty("SERVICE_URI");
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setUsePool(true);
        return factory;
    }

    @Bean
    @Autowired
    RedisTemplate<String, ServiceInstance> instanceTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, ServiceInstance> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        return template;
    }

    @Bean
    @Autowired
    RedisTemplate<String, ServiceInstanceBinding> bindingTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, ServiceInstanceBinding> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        return template;
    }
}