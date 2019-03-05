package com.margomicroservices.delivery.amqp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.margomicroservices.delivery.amqp.event.OrderDeliveredEvent;
import com.margomicroservices.delivery.model.Delivery;
import com.margomicroservices.delivery.repository.DeliveryRepository;
import com.margomicroservices.delivery.service.JsonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class QueueConsumer {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final ObjectMapper objectMapper;

    private final DeliveryRepository deliveryRepository;

    private final QueueProducer queueProducer;

    private JsonService jsonService;

    @Autowired
    public QueueConsumer(ObjectMapper objectMapper,
                         DeliveryRepository deliveryRepository,
                         QueueProducer queueProducer,
                         JsonService jsonService) {
        this.objectMapper = objectMapper;
        this.deliveryRepository = deliveryRepository;
        this.queueProducer = queueProducer;
        this.jsonService = jsonService;
    }

    @RabbitListener(queues = {"delivery_queue"})
    public void receiveMessage(String message) throws IOException, InterruptedException {
        logger.info("Received (String) " + message);

        Long orderId = objectMapper.readTree(message).path("orderId").longValue();
        Delivery delivery = new Delivery(orderId);

        deliveryRepository.save(delivery);

        Thread.sleep(3000);

        queueProducer.produce(jsonService.toJsonString(new OrderDeliveredEvent(orderId)));
    }
}
