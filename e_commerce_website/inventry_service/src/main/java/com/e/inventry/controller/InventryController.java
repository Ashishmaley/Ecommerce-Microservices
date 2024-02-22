package com.e.inventry.controller;

import org.springframework.web.bind.annotation.RestController;

import com.e.inventry.service.InventryService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/inventry")
@RequiredArgsConstructor
public class InventryController {
    
    private final InventryService service;
    @GetMapping("{sku-code}")
    public boolean isInStock(@PathVariable("sku-code") String skuCode) {
        return service.isInInventry(skuCode);
    }
}
