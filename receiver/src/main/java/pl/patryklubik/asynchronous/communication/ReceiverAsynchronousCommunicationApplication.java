package pl.patryklubik.asynchronous.communication;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReceiverAsynchronousCommunicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceiverAsynchronousCommunicationApplication.class, args);
	}

	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
