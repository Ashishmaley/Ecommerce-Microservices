package com.e.order_sevice.controller;

import org.springframework.web.bind.annotation.RestController;

import com.e.order_sevice.dto.OrderedRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String placeOrder(@RequestBody OrderedRequest orderedRequest) {
        return "order created successfully";
    }

    // @GetMapping("/getOrder")
    // public String getMethodName(@RequestParam String param) {
    //     return new String();
    // }

}
