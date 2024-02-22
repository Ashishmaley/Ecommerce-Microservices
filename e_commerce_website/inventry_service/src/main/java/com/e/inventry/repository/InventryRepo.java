package com.e.inventry.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e.inventry.model.Inventry;

public interface InventryRepo extends JpaRepository<Inventry,Long> {

    Optional<Inventry> findBySkuCode(String skuCode);
}
