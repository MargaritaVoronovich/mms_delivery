package com.margomicroservices.delivery.amqp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.margomicroservices.delivery.model.Delivery;
import com.margomicroservices.delivery.repository.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class QueueConsumer {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @RabbitListener(queues = {"order_created"})
    public void receiveMessage(String message) throws IOException, InterruptedException {
        logger.info("Received (String) " + message);

        //TODO: refactor
        Long orderId = objectMapper.readTree(message).path("order").path("id").longValue();

        Delivery delivery = new Delivery(orderId);
        deliveryRepository.save(delivery);

        Thread.sleep(3000);

        //TODO: send new event to history
        logger.info("Sending delivery event to history..");
    }
}
