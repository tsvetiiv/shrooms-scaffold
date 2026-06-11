package com.shrooms.scaffold.web;

import com.shrooms.scaffold.model.entity.customOrder.CustomOrder;
import com.shrooms.scaffold.model.entity.order.Order;
import com.shrooms.scaffold.model.entity.scaffold.Scaffold;
import com.shrooms.scaffold.service.customOrder.CustomOrderService;
import com.shrooms.scaffold.service.order.OrderService;
import com.shrooms.scaffold.service.scaffold.ScaffoldService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final OrderService  orderService;
    private final CustomOrderService  customOrderService;
    private final ScaffoldService scaffoldService;

    public AdminController(OrderService orderService, CustomOrderService customOrderService, ScaffoldService scaffoldService) {
        this.orderService = orderService;
        this.customOrderService = customOrderService;
        this.scaffoldService = scaffoldService;
    }

    @GetMapping
    public ModelAndView getAdminDashboard() {
        return new ModelAndView("admin/dashboard");
    }

    @GetMapping("/orders")
    public ModelAndView getOrders() {
        List<Order> allOrders =  orderService.getAllOrders();
        ModelAndView modelAndView = new ModelAndView("admin/orders");
        modelAndView.addObject("orders", allOrders);
        return modelAndView;
    }

    @GetMapping("/custom-orders")
    public ModelAndView getCustomOrders() {
        List<CustomOrder> allCustomOrders = customOrderService.getAllCustomOrders();
        ModelAndView modelAndView = new ModelAndView("admin/custom-orders");
        modelAndView.addObject("customOrders", allCustomOrders);
        return modelAndView;
    }

    @GetMapping("/scaffolds")
    public ModelAndView getScaffolds() {
        List<Scaffold> scaffolds = scaffoldService.findAll();
        ModelAndView modelAndView = new ModelAndView("admin/scaffolds");
        modelAndView.addObject("scaffolds", scaffolds);
        return modelAndView;
    }
}
