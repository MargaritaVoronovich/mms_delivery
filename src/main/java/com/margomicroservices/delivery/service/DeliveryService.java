package com.margomicroservices.delivery.service;

import com.margomicroservices.delivery.model.Delivery;
import com.margomicroservices.delivery.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public List<Delivery> getAll() {
        return deliveryRepository.findAll();
    }

    public Optional<Delivery> findById(Long id) {
        return deliveryRepository.findById(id);
    }

    public void deleteById(Long id) {
        deliveryRepository.deleteById(id);
    }

    public void create(Delivery delivery) {
        deliveryRepository.save(delivery);
    }
}
