package com.shrooms.scaffold.service.customOrder;

import com.shrooms.scaffold.model.dto.order.CustomOrderRequest;
import com.shrooms.scaffold.model.dto.user.UserDto;
import com.shrooms.scaffold.model.entity.customOrder.CustomOrder;
import com.shrooms.scaffold.model.entity.customOrder.RequestStatus;
import com.shrooms.scaffold.model.entity.user.User;
import com.shrooms.scaffold.repository.customRequest.CustomOrderRepository;
import com.shrooms.scaffold.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CustomOrderService {


    private final UserRepository userRepository;
    private final CustomOrderRepository customOrderRepository;

    public CustomOrderService( UserRepository userRepository, CustomOrderRepository customOrderRepository) {
        this.userRepository = userRepository;
        this.customOrderRepository = customOrderRepository;
    }

    public List<CustomOrder> getOrdersByUserId(UUID userId) {
        return customOrderRepository.findAllByUserId(userId);
    }

    public void createCustomOrder(CustomOrderRequest customRequest, UserDto userDto) {
        if (customRequest.getStartDate().isBefore(LocalDate.now())) {
            throw new RuntimeException(
                    "Start date cannot be in the past.");
        }

        if (customRequest.getEndDate().isBefore(customRequest.getStartDate())) {
            throw new RuntimeException("End date cannot be before start date.");
        }


        User user = userRepository
                .findById(userDto.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        CustomOrder customOrder = CustomOrder.builder()
                .user(user)
                .projectName(customRequest.getProjectName())
                .description(customRequest.getProjectDescription())
                .height(customRequest.getHeight())
                .width(customRequest.getWidth())
                .length(customRequest.getLength())
                .address(customRequest.getAddress())
                .contactPhone(customRequest.getContactPhone())
                .installationRequired(customRequest.isInstallationRequired())
                .orderType(customRequest.getOrderType())
                .startDate(customRequest.getStartDate())
                .endDate(customRequest.getEndDate())
                .requestStatus(RequestStatus.PENDING)
                .createdOn(LocalDate.now())
                .build();

        customOrderRepository.save(customOrder);
    }

    public List<CustomOrder> getAllCustomOrders() {
        return customOrderRepository.findAllByOrderByCreatedOnDesc();
    }

}
