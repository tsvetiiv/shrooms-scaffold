package com.shrooms.scaffold.model.dto.order;

import com.shrooms.scaffold.model.entity.customOrder.RequestStatus;
import com.shrooms.scaffold.model.entity.order.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomOrderRequest {
    private double height;
    private double width;
    private double length;

    private String address;

    private boolean installationRequired;

    private LocalDate startDate;
    private LocalDate endDate;
    private String contactPhone;
    private String projectDescription;
    private String projectName;
    private String projectImage;
    private OrderType orderType;



}
