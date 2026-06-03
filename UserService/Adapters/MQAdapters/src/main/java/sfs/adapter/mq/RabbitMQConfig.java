package sfs.adapter.mq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "sfs.exchange";
    public static final String USER_CREATED_ROUTING_KEY = "user.created";
    public static final String CLIENT_FAILED_QUEUE = "client.creation.failed.queue";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue clientFailedQueue() {
        return new Queue(CLIENT_FAILED_QUEUE, true);
    }

    @Bean
    public Binding clientFailedBinding(Queue clientFailedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(clientFailedQueue).to(exchange).with("client.creation.failed");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}