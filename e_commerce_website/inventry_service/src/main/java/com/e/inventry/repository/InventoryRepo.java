package com.e.inventry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e.inventry.model.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory, Long> {
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
