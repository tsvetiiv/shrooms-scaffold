package com.shrooms.scaffold.web;
import com.shrooms.scaffold.model.dto.user.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class ProfileController {

    @GetMapping("/profile")
    public ModelAndView getProfilePage(HttpSession session) {

        UserDto user = (UserDto) session.getAttribute("user");

        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @GetMapping("/profile/edit")
    public ModelAndView getEditProfilePage(HttpSession session) {

        UserDto user = (UserDto) session.getAttribute("user");

        ModelAndView modelAndView = new ModelAndView("edit-profile");
        modelAndView.addObject("user", user);

        return modelAndView;
    }
}