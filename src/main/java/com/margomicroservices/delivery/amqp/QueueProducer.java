package com.margomicroservices.delivery.amqp;

import com.margomicroservices.delivery.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class QueueProducer {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public QueueProducer(RabbitTemplate rabbitTemplate) {
        super();
        this.rabbitTemplate = rabbitTemplate;
    }

    void produce(String message) {
        logger.info("Storing notification...");

        rabbitTemplate.convertAndSend(RabbitConfig.HISTORY_QUEUE, message);

        logger.info("Notification stored in queue successfully");
    }
}
