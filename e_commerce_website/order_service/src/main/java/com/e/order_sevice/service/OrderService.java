package com.e.order_sevice.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.e.order_sevice.event.OrderPlaceEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import com.e.order_sevice.dto.InventoryResponse;
import com.e.order_sevice.dto.OrderedListDto;
import com.e.order_sevice.dto.OrderedRequest;
import com.e.order_sevice.models.Order;
import com.e.order_sevice.models.OrderLine;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final WebClient.Builder webClientBuilder;
    private final com.e.order_sevice.repository.OrderRepo orderRepo;
    private final KafkaTemplate<String,OrderPlaceEvent> kafkaTemplate;

    @SuppressWarnings("null")
    public String placeOrder(OrderedRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLine> orderLineList = orderRequest.getOrderedListDto().stream()
                .map(this::mapToOrderLine)
                .toList();

        order.setOrderLineItems(orderLineList);

        List<String> skuCodeList = orderLineList.stream()
                .map(OrderLine::getSkuCode)
                .toList();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://inventory-service")
                .path("/api/inventory/isInStock")
                .queryParam("skuCode", skuCodeList.toArray());

        System.out.println("Generated URI: " + uriBuilder.build().toUriString());

        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri(uriBuilder::build)
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allInStock = skuCodeList.size() == inventoryResponses.length;

        if (allInStock) {
            orderRepo.save(order);
            kafkaTemplate.send("order-topic", new OrderPlaceEvent(order.getOrderId()));
            return "order placed successfully";
        } else {
            throw new IllegalStateException("Product is not available");
        }
    }

    private OrderLine mapToOrderLine(OrderedListDto od) {
        OrderLine orderLine = new OrderLine();
        orderLine.setPrice(od.getPrice());
        orderLine.setQuantity(od.getQuantity());
        orderLine.setSkuCode(od.getSkuCode());
        return orderLine;
    }
}