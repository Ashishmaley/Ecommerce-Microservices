package com.e.inventry.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e.inventry.repository.InventryRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventryService {
    private final InventryRepo inventryRepo;

    public boolean isInInventry(String skuCode) {
        return inventryRepo.findBySkuCode(skuCode).isPresent();
    }
}
