package com.shrooms.scaffold.model.dto.order;
;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentOrderRequest {

    private UUID scaffoldId;
    private Integer quantity;
    private Integer rentalWeeks;
    private String address;
    private boolean installationRequired;


}
