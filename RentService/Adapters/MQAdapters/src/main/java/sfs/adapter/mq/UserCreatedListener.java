package sfs.adapter.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import sfs.domain.model.Client;
import sfs.ports.api.ClientService;
import sfs.ports.api.MessagePublisherPort;

import java.util.Map;

@Component
public class UserCreatedListener {

    private final ClientService clientService;
    private final MessagePublisherPort messagePublisherPort;

    public UserCreatedListener(ClientService clientService, MessagePublisherPort messagePublisherPort) {
        this.clientService = clientService;
        this.messagePublisherPort = messagePublisherPort;
    }

    @RabbitListener(queues = RabbitMQConfig.USER_CREATED_QUEUE)
    public void handleUserCreated(Map<String, String> event) {
        String userId = event.get("userId");
        String firstName = event.get("firstName");
        String lastName = event.get("lastName");

        try {
            Client client = new Client(userId, firstName, lastName);
            clientService.createClient(client);
        } catch (Exception e) {
            messagePublisherPort.publishClientCreationFailedEvent(userId, e.getMessage());
        }
    }
}