package com.e.order_sevice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedListDto {
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private int quantity;
}
