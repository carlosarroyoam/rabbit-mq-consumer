package com.carlosarroyoam.service.rabbitmq;

import com.carlosarroyoam.service.rabbitmq.config.RabbitMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConsumer {
  private static final Logger log = LoggerFactory.getLogger(RabbitMqConsumer.class);

  @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
  public void receiveMessage(String message) {
    log.info("Received message: {}", message);
  }
}
