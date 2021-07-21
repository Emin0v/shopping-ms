package com.company.service;

import com.company.entity.Order;

public interface OrderNotificationService {

    void sendNotificationToQueue(Order order);
}
