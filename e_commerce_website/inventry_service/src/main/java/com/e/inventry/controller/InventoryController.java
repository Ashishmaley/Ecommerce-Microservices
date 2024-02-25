package com.e.inventry.controller;

import org.springframework.web.bind.annotation.RestController;

import com.e.inventry.dto.InventoryResponse;
import com.e.inventry.service.InventoryService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService service;

    @GetMapping("/isInStock")
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        System.out.println("Request Body: " + skuCode);
        return service.isInInventry(skuCode);
    }

}
