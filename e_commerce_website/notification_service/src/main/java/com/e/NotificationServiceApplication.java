package com.e;

import com.e.model.OrderPlaceEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }
    @KafkaListener(topics = "order-topic")
    public void listen(OrderPlaceEvent orderPlaceEvent) {
        log.info("Received notification for order - {}" ,orderPlaceEvent.getOrderId());
    }
}
