package com.user.demo.listerner;



import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HelloQueueListener {

    @RabbitListener(queues = "hello")
    public void handler(Message message) {
        System.out.println("收到消息"+message);
    }
}
