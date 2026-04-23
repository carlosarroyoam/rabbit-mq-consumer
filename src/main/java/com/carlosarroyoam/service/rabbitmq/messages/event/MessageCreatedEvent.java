package com.carlosarroyoam.service.rabbitmq.messages.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageCreatedEvent {
  private Integer id;
  private String content;
}
