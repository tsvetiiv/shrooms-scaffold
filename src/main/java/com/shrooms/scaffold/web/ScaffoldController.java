package com.shrooms.scaffold.web;

import com.shrooms.scaffold.service.scaffold.ScaffoldService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/scaffolds")
public class ScaffoldController {

    private final ScaffoldService scaffoldService;

    public ScaffoldController(ScaffoldService scaffoldService) {
        this.scaffoldService = scaffoldService;
    }
    @GetMapping
    public ModelAndView getScaffoldsPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("scaffolds");
        modelAndView.addObject("scaffolds", scaffoldService.findAll());
        return modelAndView;
    }
}
