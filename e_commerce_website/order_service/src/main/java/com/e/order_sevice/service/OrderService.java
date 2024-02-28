package com.e.order_sevice.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
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

    private final WebClient webClient;
    private final com.e.order_sevice.repository.OrderRepo orderRepo;

    @SuppressWarnings("null")
    public void placeOrder(OrderedRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLine> orderLineList = orderRequest.getOrderedListDto().stream()
                .map(this::mapToOrderLine) // Use method reference for simplicity
                .toList();

        order.setOrderLineItems(orderLineList);

        List<String> skuCodeList = orderLineList.stream()
                .map(OrderLine::getSkuCode)
                .collect(Collectors.toList());

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost:8082")
                .path("/api/inventory/isInStock")
                .queryParam("skuCode", skuCodeList.toArray());

        System.out.println("Generated URI: " + uriBuilder.build().toUriString());

        InventoryResponse[] inventoryResponses = webClient.get()
                .uri(uriBuilder::build)
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allInStock = (skuCodeList.size() == inventoryResponses.length) ? true : false;

        if (allInStock) {
            orderRepo.save(order);
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