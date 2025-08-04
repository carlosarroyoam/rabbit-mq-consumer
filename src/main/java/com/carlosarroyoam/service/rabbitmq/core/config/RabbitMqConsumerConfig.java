package com.carlosarroyoam.service.rabbitmq.core.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConsumerConfig {
  public static final String MESSAGES_EXCHANGE_NAME = "messages.exchange";
  public static final String MESSAGES_CREATED_QUEUE_NAME = "messages-created-queue";
  public static final String MESSAGES_CREATED_ROUTING_KEY = "messages.created.key";

  @Bean
  Queue queue() {
    return new Queue(MESSAGES_CREATED_QUEUE_NAME, true);
  }

  @Bean
  Exchange exchange() {
    return new DirectExchange(MESSAGES_EXCHANGE_NAME);
  }

  @Bean
  Binding binding(Queue queue, Exchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(MESSAGES_CREATED_ROUTING_KEY).noargs();
  }

  @Bean
  SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
      ConnectionFactory connectionFactory, MessageConverter messageConverter) {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setMessageConverter(messageConverter);
    return factory;
  }

  @Bean
  Jackson2JsonMessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
