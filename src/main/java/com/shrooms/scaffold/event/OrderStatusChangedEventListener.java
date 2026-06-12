package com.shrooms.scaffold.event;

import com.shrooms.scaffold.service.email.EmailService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusChangedEventListener {

    private final EmailService emailService;

    public OrderStatusChangedEventListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @EventListener
    public void onOrderStatusChanged(OrderStatusChangedEvent event) {
        try {
            emailService.sendOrderStatusChangedEmail(event);
        } catch (RuntimeException exception) {
            System.out.println("Failed to send order status email: " + exception.getMessage());
        }
    }
}
