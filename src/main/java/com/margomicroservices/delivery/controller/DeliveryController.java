package com.margomicroservices.delivery.controller;

import com.margomicroservices.delivery.model.Delivery;
import com.margomicroservices.delivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @GetMapping("/deliveries")
    public ResponseEntity<List<Delivery>> all() {
        return ResponseEntity.ok(deliveryService.getAll());
    }

    @GetMapping("/deliveries/{id}")
    public ResponseEntity<?> one(@PathVariable final Long id) {
        final Optional<Delivery> delivery = deliveryService.findById(id);

        if (!delivery.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(delivery.get());
    }

    @PostMapping("/deliveries")
    public ResponseEntity<?> create(@Valid @RequestBody final Delivery order) throws URISyntaxException {
        deliveryService.create(order);

        return ResponseEntity.noContent().build();
    }
}
