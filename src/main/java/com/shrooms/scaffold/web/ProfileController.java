package com.shrooms.scaffold.web;
import com.shrooms.scaffold.model.dto.user.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping("/profile/edit")
    public String editProfile(UserDto user, HttpSession session) {
        UserDto currentUser = (UserDto) session.getAttribute("user");

        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setEmail(user.getEmail());
        currentUser.setProfilePicture(user.getProfilePicture());
        session.setAttribute("user", currentUser);

        return "redirect:/users/profile";
    }
}