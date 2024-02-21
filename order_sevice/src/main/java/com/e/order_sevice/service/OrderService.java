package com.e.order_sevice.service;

import java.util.UUID;
import org.springframework.stereotype.Service;
import com.e.order_sevice.dto.OrderedListDto;
import com.e.order_sevice.dto.OrderedRequest;
import com.e.order_sevice.models.Order;
import com.e.order_sevice.models.OrderLine;
import com.e.order_sevice.repository.OrderRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepo orderRepo;

    void placeOrder(OrderedRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        java.util.List<OrderLine> orderLineList = orderRequest.getOrderedListDto().
        stream().
                map(orderLineDto -> mapTODto(orderLineDto)).toList();

        order.setOrderLineItems(orderLineList);
        orderRepo.save(order);
    }
    private OrderLine mapTODto(OrderedListDto od) {
        OrderLine orderLine = new OrderLine();
        orderLine.setId(od.getId());
        orderLine.setPrice(od.getPrice());
        orderLine.setQuantity(od.getQuantity());
        orderLine.setSkuCode(orderLine.getSkuCode());
        return orderLine;
    }
}
