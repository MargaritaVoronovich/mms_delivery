package com.margomicroservices.delivery.config;

import com.margomicroservices.delivery.amqp.QueueConsumer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String HISTORY_QUEUE = "history_queue";

    private static final String LISTENER_METHOD = "receiveMessage";
    private static final String DELIVERY_QUEUE = "delivery_queue";

    @Value("${topic.exchange}")
    private String topicExchange;
    @Value("${routing.key}")
    private String routingKey;

    @Bean
    Queue historyQueue() {
        return new Queue(HISTORY_QUEUE, true);
    }

    @Bean
    Queue deliveryQueue() {
        return new Queue(DELIVERY_QUEUE, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchange);
    }

    @Bean
    Binding historyBinding(TopicExchange exchange) {
        return BindingBuilder.bind(historyQueue()).to(exchange).with(routingKey);
    }

    @Bean
    Binding deliveryBinding(TopicExchange exchange) {
        return BindingBuilder.bind(deliveryQueue()).to(exchange).with(routingKey);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(DELIVERY_QUEUE);
        container.setMessageListener(listenerAdapter);

        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(QueueConsumer consumer) {
        return new MessageListenerAdapter(consumer, LISTENER_METHOD);
    }

}
