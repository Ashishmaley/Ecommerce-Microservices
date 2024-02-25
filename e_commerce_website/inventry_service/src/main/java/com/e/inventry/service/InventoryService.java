package com.e.inventry.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e.inventry.dto.InventoryResponse;
import com.e.inventry.repository.InventoryRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryService {
    private final InventoryRepo inventoryRepo;

    public List<InventoryResponse> isInInventry(List<String> skuCode) {
        return inventoryRepo.findBySkuCodeIn(skuCode).stream().map(inventory -> InventoryResponse.builder()
                .skuCode(inventory.getSkuCode())
                .inStock(inventory.getQuantity() > 0)
                .build()).toList();
    }
}
