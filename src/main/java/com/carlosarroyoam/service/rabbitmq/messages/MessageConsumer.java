package com.carlosarroyoam.service.rabbitmq.messages;

import com.carlosarroyoam.service.rabbitmq.core.config.RabbitMqConsumerConfig;
import com.carlosarroyoam.service.rabbitmq.messages.event.MessageCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
  private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);

  @RabbitListener(queues = RabbitMqConsumerConfig.MESSAGES_CREATED_QUEUE_NAME)
  public void receiveMessage(MessageCreatedEvent event) {
    log.info("Received message: {}", event);
  }
}
