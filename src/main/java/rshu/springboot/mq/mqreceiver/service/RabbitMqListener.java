package rshu.springboot.mq.mqreceiver.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import rshu.springboot.mq.mqreceiver.MqReceiverApplication;

//import com.google.gson.Gson;

@Service
public class RabbitMqListener {
    static int s = 0;

    @RabbitListener(queues = MqReceiverApplication.QueueName)
    public void listen(final Notification message) {
        System.out.printf("%d: Received a new notification: %s\n", s++, message.toString());
    }
}
