package com.margomicroservices.delivery.amqp.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderDeliveredEvent {
    private ObjectMapper objectMapper;

    @JsonProperty
    private final Long time;

    @JsonProperty
    private final String status = "delivered";

    @JsonProperty
    private final Long orderId;

    public OrderDeliveredEvent(Long orderId, ObjectMapper objectMapper) {
        this.orderId = orderId;
        this.objectMapper = objectMapper;

        time = System.currentTimeMillis();
    }

    public String toJsonString() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this);
    }

    public Long getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public Long getOrderId() {
        return orderId;
    }
}
