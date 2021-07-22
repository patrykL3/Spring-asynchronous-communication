package pl.patryklubik.asynchronous.communication.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.patryklubik.asynchronous.communication.model.Notification;

/**
 * Create by Patryk Łubik on 22.07.2021.
 */

@RestController
public class MessageController {
    private final RabbitTemplate rabbitTemplate;

    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //To use this method, comment out @RabbitListener,
    // otherwise all messages in "que" will be "stolen" by the listener.
    @GetMapping("/notification")
    public ResponseEntity<Notification> receiveNotification() {
        Notification notification = rabbitTemplate.
                receiveAndConvert("que", ParameterizedTypeReference.forType(Notification.class));
        if (notification != null) {
            return ResponseEntity.ok(notification);
        }
        return ResponseEntity.noContent().build();
    }

    @RabbitListener(queues = "que")
    public void listenerMessage(Notification notification) {
        System.out.println("Title: " + notification.getTitle() + " Description: " + notification.getDescription());
    }
}