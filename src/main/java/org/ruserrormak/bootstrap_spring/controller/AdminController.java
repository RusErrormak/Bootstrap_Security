package org.ruserrormak.bootstrap_spring.controller;


import org.ruserrormak.bootstrap_spring.model.Users;
import org.ruserrormak.bootstrap_spring.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private final UsersService userService;

    @Autowired
    public AdminController(UsersService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUsers(ModelMap model, Authentication authentication) {
        Users user = userService.findByUsername(authentication.getName());
        model.addAttribute("user", user);
        model.addAttribute("users", userService.allUsers());
        return "admin/showUsers";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute Users user, @RequestParam(value = "selectedRoles", required = false) String selectedRoles) {
        String[] roles = selectedRoles != null ? selectedRoles.split(",") : new String[0];
        userService.update(user, roles);
        return "redirect:/admin";
    }

    @GetMapping("/addUser")
    public String showAddForm(ModelMap model) {
        Users user = new Users();
        user.setUsername("");
        user.setEmail("");
        user.setPassword("");
        model.addAttribute("user", user);
        return "admin/addUser";
    }

    @PostMapping("/newUser")
    public String createUser(@ModelAttribute Users user, @RequestParam(value = "selectedRoles", required = false) String selectedRoles) {
        String[] roles = selectedRoles != null ? selectedRoles.split(",") : new String[0];
        userService.saveUser(user, roles);
        return "redirect:/admin";
    }

}
