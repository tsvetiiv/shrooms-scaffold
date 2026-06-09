package com.shrooms.scaffold.web;

import com.shrooms.scaffold.model.dto.order.RentOrderRequest;
import com.shrooms.scaffold.model.dto.user.UserDto;
import com.shrooms.scaffold.service.order.OrderService;
import com.shrooms.scaffold.service.scaffold.ScaffoldService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/scaffolds/rent")
public class RentController {

    private final OrderService orderService;
    private final ScaffoldService scaffoldService;

    public RentController(OrderService orderService, ScaffoldService scaffoldService) {
        this.orderService = orderService;
        this.scaffoldService = scaffoldService;
    }

    @GetMapping
    public ModelAndView getRentPage() {

        ModelAndView modelAndView = new ModelAndView("rent");
        modelAndView.addObject("scaffolds", scaffoldService.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getRentForm(@PathVariable UUID id) {

        ModelAndView modelAndView = new ModelAndView("rent-form");
        modelAndView.addObject("scaffold", scaffoldService.findById(id));
        modelAndView.addObject("rentOrderRequest", new RentOrderRequest());

        return modelAndView;
    }

    @PostMapping("/{id}")
    public String rentScaffold(@PathVariable UUID id,
                               @ModelAttribute RentOrderRequest rentOrderRequest,
                               HttpSession session) {

        UserDto user = (UserDto) session.getAttribute("user");

        rentOrderRequest.setScaffoldId(id);

        orderService.createRentOrder(rentOrderRequest, user);

        return "redirect:/orders";
    }

}