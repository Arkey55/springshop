package ru.romankuznetsov.integration;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendOrderToRabbitMQ {

    private final static String QUEUE = "order";

    public void sendOrder(List list) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE, false, false, false, null);
            channel.basicPublish("", QUEUE, null, list.toString().getBytes());
            System.out.println(list.toString());
        }
    }
}
