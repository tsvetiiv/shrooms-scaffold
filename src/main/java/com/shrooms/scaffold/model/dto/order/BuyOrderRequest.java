package com.shrooms.scaffold.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyOrderRequest {

    private UUID scaffoldId;
    private Integer quantity;
    private String address;
    private boolean installationRequired;
}