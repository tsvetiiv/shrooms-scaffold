package com.shrooms.scaffold.model.dto.order;

import com.shrooms.scaffold.model.entity.order.OrderType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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

    @NotNull
    @Positive
    private Double height;
    @NotNull
    @Positive
    private Double width;
    @NotNull
    @Positive
    private Double length;
    @NotBlank(message = "Delivery address is required")
    @Size(min = 5, max = 150, message = "Delivery address must be between 5 and 150 characters")
    private String address;

    private boolean installationRequired;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotBlank
    @Size(min = 10, max = 15)
    private String contactPhone;
    @NotBlank
    @Size(min = 20, max = 150)
    private String projectDescription;
    @NotBlank
    @Size(min = 3, max = 80)
    private String projectName;
    @Size(max = 500)
    private String projectImage;
    @NotNull
    private OrderType orderType;
}
