package com.user.demo.listerner;



import com.common.demo.constant.Constants;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class UserQueueListener {

//    @RabbitListener(queues = Constants.USER_QUEUE_NAME)
//    public void handle(Message message , Channel channel) throws IOException {
//        long deliveryTag = message.getMessageProperties().getDeliveryTag();
//
//        try {
//            String body = new String(message.getBody());
//            log.info("收到用户信息, body:{}", body);
//            //TODO 发送注册成功邮件
//
//            //确认
//            channel.basicAck(deliveryTag,true);
//        } catch (Exception e) {
//            //否定确认
//            channel.basicNack(deliveryTag,true,true);
//            log.error("邮件发送失败, e:", e);
//        }
//    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = Constants.USER_QUEUE_NAME, durable = "true"),
            exchange = @Exchange(value = Constants.USER_EXCHANGE_NAME, type = ExchangeTypes.FANOUT)
    ))
    public void handle(Message message , Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            String body = new String(message.getBody());
            log.info("收到用户信息, body:{}", body);
            //TODO 发送注册成功邮件

            //确认
            channel.basicAck(deliveryTag,true);
        } catch (Exception e) {
            //否定确认
            channel.basicNack(deliveryTag,true,true);
            log.error("邮件发送失败, e:", e);
        }
    }
}
