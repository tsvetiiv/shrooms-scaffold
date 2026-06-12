package com.shrooms.scaffold.web;

import com.shrooms.scaffold.model.dto.scaffold.ScaffoldRequest;
import com.shrooms.scaffold.model.entity.customOrder.CustomOrder;
import com.shrooms.scaffold.model.entity.customOrder.RequestStatus;
import com.shrooms.scaffold.model.entity.order.Order;
import com.shrooms.scaffold.model.entity.order.OrderStatus;
import com.shrooms.scaffold.model.entity.scaffold.MaterialType;
import com.shrooms.scaffold.model.entity.scaffold.Scaffold;
import com.shrooms.scaffold.model.entity.scaffold.ScaffoldCategory;
import com.shrooms.scaffold.repository.scaffold.ScaffoldRepository;
import com.shrooms.scaffold.service.customOrder.CustomOrderService;
import com.shrooms.scaffold.service.order.OrderService;
import com.shrooms.scaffold.service.scaffold.ScaffoldService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final OrderService orderService;
    private final CustomOrderService customOrderService;
    private final ScaffoldService scaffoldService;
    private final ScaffoldRepository scaffoldRepository;

    public AdminController(OrderService orderService, CustomOrderService customOrderService, ScaffoldService scaffoldService, ScaffoldRepository scaffoldRepository) {
        this.orderService = orderService;
        this.customOrderService = customOrderService;
        this.scaffoldService = scaffoldService;
        this.scaffoldRepository = scaffoldRepository;
    }

    @GetMapping
    public ModelAndView getAdminDashboard() {
        return new ModelAndView("admin/dashboard");
    }

    @GetMapping("/orders")
    public ModelAndView getOrders() {
        List<Order> allOrders = orderService.getAllOrders();
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

    @PutMapping("/orders/{id}/status")
    public String updateOrderStatus(@PathVariable UUID id, @RequestParam OrderStatus orderStatus) {
        orderService.updateOrderStatus(id, orderStatus);
        return "redirect:/admin/orders";
    }

    @PutMapping("/custom-orders/{id}")
    public String updateCustomOrder(@PathVariable UUID id,
                                    @RequestParam RequestStatus requestStatus,
                                    @RequestParam BigDecimal estimatedPrice) {
        customOrderService.updateCustomOrder(id, requestStatus, estimatedPrice);
        return "redirect:/admin/custom-orders";
    }

    @GetMapping("/scaffolds/{id}/edit")
    public ModelAndView getEditScaffold(@PathVariable UUID id) {
        ScaffoldRequest scaffoldRequest = scaffoldService.getScaffoldForEdit(id);

        ModelAndView modelAndView = new ModelAndView("admin/edit-scaffold");
        modelAndView.addObject("scaffoldRequest", scaffoldRequest);
        modelAndView.addObject("scaffoldId", id);
        modelAndView.addObject("materialTypes", MaterialType.values());
        modelAndView.addObject("scaffoldCategories", ScaffoldCategory.values());

        return modelAndView;
    }

    @PutMapping("/scaffolds/{id}")
    public String editScaffold(@PathVariable UUID id,
                               @Valid @ModelAttribute("scaffoldRequest") ScaffoldRequest scaffoldRequest,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/edit-scaffold";
        }

        scaffoldService.editScaffold(id, scaffoldRequest);

        return "redirect:/admin/scaffolds";
    }

    @GetMapping("/scaffolds/create")
    public ModelAndView getCreateScaffold(Model model) {
        ModelAndView modelAndView = new ModelAndView("admin/create-scaffold");
        modelAndView.addObject("scaffoldRequest", new ScaffoldRequest());
        modelAndView.addObject("scaffoldCategories", ScaffoldCategory.values());
        modelAndView.addObject("materialTypes", MaterialType.values());
        return modelAndView;
    }

    @PostMapping("/scaffolds")
    public ModelAndView createScaffold(@Valid @ModelAttribute("scaffoldRequest") ScaffoldRequest scaffoldRequest,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("admin/create-scaffold");
            modelAndView.addObject("scaffoldCategories", ScaffoldCategory.values());
            modelAndView.addObject("materialTypes", MaterialType.values());
            return modelAndView;
        }

        scaffoldService.createScaffold(scaffoldRequest);

        return new ModelAndView("redirect:/admin/scaffolds");
    }

    @DeleteMapping("/scaffolds/{id}")
    public ModelAndView deleteScaffold(@PathVariable UUID id, RedirectAttributes redirectAttributes) {

        boolean deleted = scaffoldService.deleteScaffold(id);

        if (deleted) {
            redirectAttributes.addFlashAttribute("successMessage", "Scaffold deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("warningMessage", "Scaffold has existing orders and was marked as unavailable.");
        }

        return new ModelAndView("redirect:/admin/scaffolds");
    }
}
