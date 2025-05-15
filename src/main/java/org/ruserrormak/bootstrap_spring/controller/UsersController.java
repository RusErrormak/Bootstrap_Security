package org.ruserrormak.bootstrap_spring.controller;

import org.ruserrormak.bootstrap_spring.model.Users;
import org.ruserrormak.bootstrap_spring.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UsersController {


    private final UsersService userService;

    @Autowired
    public UsersController(UsersService userService) { this.userService = userService; }

    @GetMapping("/info")
    public String showUserInfo(ModelMap model, Authentication authentication) {
        Users user = userService.findByUsername(authentication.getName());
        model.addAttribute("user", user);
        return "user/info";
    }
}
