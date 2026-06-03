package sfs.adapter.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import sfs.ports.api.UserService;

import java.util.Map;

@Component
public class ClientCreationFailedListener {

    private final UserService userService;

    public ClientCreationFailedListener(UserService userService) {
        this.userService = userService;
    }

    @RabbitListener(queues = RabbitMQConfig.CLIENT_FAILED_QUEUE)
    public void handleClientCreationFailed(Map<String, String> event) {
        String userId = event.get("userId");
        try {
            // Kompensacja błędu - np. usunięcie/deaktywacja usera
            userService.deactivateUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}