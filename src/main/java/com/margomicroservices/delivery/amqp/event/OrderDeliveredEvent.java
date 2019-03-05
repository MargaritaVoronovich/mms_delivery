package com.margomicroservices.delivery.amqp.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.margomicroservices.delivery._enum.order.OrderStatus;
import lombok.Data;

@Data
public class OrderDeliveredEvent {
    @JsonProperty
    private final Long time;

    @JsonProperty
    private final OrderStatus status = OrderStatus.DELIVERED;

    @JsonProperty
    private final Long orderId;

    public OrderDeliveredEvent(Long orderId) {
        this.orderId = orderId;

        time = System.currentTimeMillis();
    }
}
