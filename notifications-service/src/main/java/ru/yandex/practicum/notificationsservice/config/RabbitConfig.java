package ru.yandex.practicum.notificationsservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
//    @Value("${routing-key.accountRK}")
//    private String accountRK;
//    @Value("${routing-key.transferRK}")
//    private String transferRK;
//    @Value("${routing-key.cashRK}")
//    private String cashRK;
//
//    // Main RMQConfig
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
//        cachingConnectionFactory.setUsername("user");
//        cachingConnectionFactory.setPassword("user");
//        cachingConnectionFactory.setVirtualHost("bank");
//        return cachingConnectionFactory;
//    }
//
//    @Bean
//    public AmqpAdmin amqpAdmin() {
//        return new RabbitAdmin(connectionFactory());
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        return new RabbitTemplate(connectionFactory());
//    }
//
//    @Bean
//    public DirectExchange exchange() {
//        return new DirectExchange("bankApp", true, false);
//    }
//
//    // Account RMQConfig
//    @Bean(name = "accountQueue")
//    public Queue accountQueue() {
//        return  new Queue("account");
//    }
//
//    @Bean(name = "accountBind")
//    public Binding accounrBinding(@Qualifier("accountQueue") Queue queue, DirectExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(accountRK);
//    }
//
//    // Transfer RMQConfig
//    @Bean(name = "transferQueue")
//    public Queue transferQueue() {
//        return  new Queue("transfer");
//    }
//
//    @Bean(name = "transferBind")
//    public Binding transferBinding(@Qualifier("transferQueue") Queue queue, DirectExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(transferRK);
//    }
//
//    // Cash RMQConfig
//    @Bean(name = "cashQueue")
//    public Queue cashQueue() {
//        return  new Queue("cash");
//    }
//
//    @Bean(name = "cashBind")
//    public Binding cashBinding(@Qualifier("cashQueue") Queue queue, DirectExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(cashRK);
//    }
}