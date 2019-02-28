package com.margomicroservices.delivery.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void receiveMessage(String message) {
        logger.info("Received (String) " + message);
    }

    public void receiveMessage(byte[] message) {
        String strMessage = new String(message);

        logger.info("Received (No String) " + strMessage);
    }
}
