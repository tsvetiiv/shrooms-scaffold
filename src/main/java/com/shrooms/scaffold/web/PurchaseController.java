package com.shrooms.scaffold.web;

import com.shrooms.scaffold.model.dto.order.PurchaseOrderRequest;
import com.shrooms.scaffold.model.dto.user.UserDto;
import com.shrooms.scaffold.service.order.OrderService;
import com.shrooms.scaffold.service.scaffold.ScaffoldService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/scaffolds/purchase")
public class PurchaseController {

    private final OrderService orderService;
    private final ScaffoldService scaffoldService;

    public PurchaseController(OrderService orderService, ScaffoldService scaffoldService) {
        this.orderService = orderService;
        this.scaffoldService = scaffoldService;
    }

    @GetMapping
    public ModelAndView getPurchasePage(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView modelAndView = new ModelAndView("purchase");
        modelAndView.addObject("scaffolds", scaffoldService.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getPurchaseForm(@PathVariable UUID id, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView modelAndView = new ModelAndView("purchase-form");
        modelAndView.addObject("scaffold", scaffoldService.findById(id));
        modelAndView.addObject("purchaseOrderRequest", new PurchaseOrderRequest());

        return modelAndView;
    }

    @PostMapping("/{id}")
    public String purchaseScaffold(@PathVariable UUID id,
                               @ModelAttribute PurchaseOrderRequest purchaseOrderRequest,
                               HttpSession session) {

        UserDto user = (UserDto) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        purchaseOrderRequest.setScaffoldId(id);

        orderService.createPurchaseOrder(purchaseOrderRequest, user);

        return "redirect:/orders";
    }


}
