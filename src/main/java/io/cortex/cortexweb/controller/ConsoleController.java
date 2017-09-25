package io.cortex.cortexweb.controller;

import io.cortex.cortexweb.model.User;
import io.cortex.cortexweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class ConsoleController {
    private UserService userService;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/console-overview")
    public String showConsoleOverviewPage(Model model, Principal principal) {
        model.addAttribute("currentUserInfo", userService.findUserByUsername(currentUser(principal)));
        return "console-overview";
    }

    @RequestMapping("/console-classify")
    public String showConsoleClassifyPage(Model model, Principal principal) {
        model.addAttribute("currentUserInfo", userService.findUserByUsername(currentUser(principal)));
        return "console-classify";
    }

    @RequestMapping("/console-train")
    public String showConsoleTrainPage(Model model, Principal principal) {
        model.addAttribute("currentUserInfo", userService.findUserByUsername(currentUser(principal)));
        return "console-train";
    }

    @RequestMapping("/console-classifiers")
    public String showConsoleClassifiersPage(Model model, Principal principal) {
        model.addAttribute("currentUserInfo", userService.findUserByUsername(currentUser(principal)));
        return "console-classifiers";
    }

    private String currentUser(Principal principal) {
        User u = userService.findUserByEmail(principal.getName());
        String user = u.getUsername();

        return user;
    }
}
