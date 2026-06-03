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
    public void publishClientCreationFailedEvent(String userId, String reason) {
        Map<String, String> event = new HashMap<>();
        event.put("userId", userId);
        event.put("reason", reason);

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "client.creation.failed", event);
    }
}