package com.carlosarroyoam.service.rabbitmq.core.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component("rabbitMqProps")
@ConfigurationProperties(prefix = "app.rabbitmq")
@Validated
@Getter
@Setter
public class RabbitMqProps {
  @NotBlank(message = "app.rabbitmq.message-exchange must not be empty")
  private String messageExchange;

  @NotBlank(message = "app.rabbitmq.message-created-queue must not be empty")
  private String messageCreatedQueue;

  @NotBlank(message = "app.rabbitmq.message-created-routing-key must not be empty")
  private String messageCreatedRoutingKey;

  @NotBlank(message = "app.rabbitmq.message-dead-letter-exchange must not be empty")
  private String messageDeadLetterExchange;

  @NotBlank(message = "app.rabbitmq.message-created-dead-letter-queue must not be empty")
  private String messageCreatedDeadLetterQueue;

  @NotBlank(message = "app.rabbitmq.message-created-dead-letter-routing-key must not be empty")
  private String messageCreatedDeadLetterRoutingKey;
}
