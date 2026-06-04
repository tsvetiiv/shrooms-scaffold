package com.shrooms.scaffold.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrdersController {

    @GetMapping("/orders")
    public ModelAndView getOrdersPage(){
        return new ModelAndView("orders");
    }
}
