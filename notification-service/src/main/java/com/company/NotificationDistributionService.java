package com.company;

import com.company.messaging.OrderNotification;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class NotificationDistributionService {

    @StreamListener(Sink.INPUT)
    public void onNotification(OrderNotification orderNotification){
        System.out.println("———————————————————————————————————————————");
        System.out.println("the notification came successfully");
        System.out.println("Notification -> " + orderNotification.toString());

    }

}
