package pl.patryklubik.asynchronous.communication.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.patryklubik.asynchronous.communication.model.Notification;

/**
 * Create by Patryk Łubik on 19.07.2021.
 */

@RestController
public class MessageController {
    private final RabbitTemplate rabbitTemplate;

    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/notification")
    public String sendNotification(@RequestBody Notification notification) {
        rabbitTemplate.convertAndSend("que", notification);
        return "Notyfikacja wysłana na RabbitMq!";
    }
}