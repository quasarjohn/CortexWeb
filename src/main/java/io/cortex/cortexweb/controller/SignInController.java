package io.cortex.cortexweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignInController {

    @RequestMapping("/console-overview")
    public String showConsoleOverviewPage() {
        return "console-overview";
    }
}
