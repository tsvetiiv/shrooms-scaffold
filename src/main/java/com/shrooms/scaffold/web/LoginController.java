package com.shrooms.scaffold.web;

import com.shrooms.scaffold.model.dto.user.UserDto;
import com.shrooms.scaffold.model.dto.user.UserLoginRequest;
import com.shrooms.scaffold.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    private UserService userService;


    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage(){
        UserLoginRequest userLoginRequest = UserLoginRequest.builder().build();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("userLoginData", userLoginRequest);


        return modelAndView;
    }
    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute UserLoginRequest userLoginRequest) {

        try {
            userService.login(userLoginRequest);

            return new ModelAndView("redirect:/");

        } catch (RuntimeException exception) {

            ModelAndView modelAndView = new ModelAndView();

            modelAndView.setViewName("login");

            modelAndView.addObject("userLoginData", userLoginRequest);

            modelAndView.addObject("loginError",
                    exception.getMessage());

            return modelAndView;
        }
    }
}
