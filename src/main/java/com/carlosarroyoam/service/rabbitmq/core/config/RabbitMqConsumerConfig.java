package com.carlosarroyoam.service.rabbitmq.core.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConsumerConfig {
  @Bean
  Queue messageCreatedQueue(RabbitMqProps rabbitMqProps) {
    return QueueBuilder.durable(rabbitMqProps.getMessageCreatedQueue())
        .deadLetterExchange(rabbitMqProps.getMessageDeadLetterExchange())
        .deadLetterRoutingKey(rabbitMqProps.getMessageCreatedDeadLetterRoutingKey())
        .build();
  }

  @Bean
  Queue messageCreatedDeadLetterQueue(RabbitMqProps rabbitMqProps) {
    return QueueBuilder.durable(rabbitMqProps.getMessageCreatedDeadLetterQueue()).build();
  }

  @Bean
  DirectExchange messageExchange(RabbitMqProps rabbitMqProps) {
    return new DirectExchange(rabbitMqProps.getMessageExchange(), true, false);
  }

  @Bean
  DirectExchange messageDeadLetterExchange(RabbitMqProps rabbitMqProps) {
    return new DirectExchange(rabbitMqProps.getMessageDeadLetterExchange(), true, false);
  }

  @Bean
  Binding messageCreatedBinding(
      @Qualifier("messageCreatedQueue") Queue queue,
      @Qualifier("messageExchange") DirectExchange exchange,
      RabbitMqProps rabbitMqProps) {
    return BindingBuilder.bind(queue)
        .to(exchange)
        .with(rabbitMqProps.getMessageCreatedRoutingKey());
  }

  @Bean
  Binding messageCreatedDeadLetterBinding(
      @Qualifier("messageCreatedDeadLetterQueue") Queue queue,
      @Qualifier("messageDeadLetterExchange") DirectExchange exchange,
      RabbitMqProps rabbitMqProps) {
    return BindingBuilder.bind(queue)
        .to(exchange)
        .with(rabbitMqProps.getMessageCreatedDeadLetterRoutingKey());
  }

  @Bean
  Jackson2JsonMessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
