package sfs.adapter.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import sfs.ports.api.MessagePublisherPort;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessagePublisherAdapter implements MessagePublisherPort {

    private final RabbitTemplate rabbitTemplate;

    public MessagePublisherAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publishUserCreatedEvent(String userId, String firstName, String lastName) {
        Map<String, String> event = new HashMap<>();
        event.put("userId", userId);
        event.put("firstName", firstName);
        event.put("lastName", lastName);

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.USER_CREATED_ROUTING_KEY, event);
    }
}