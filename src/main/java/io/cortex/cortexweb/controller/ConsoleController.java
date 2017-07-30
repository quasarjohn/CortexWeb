package io.cortex.cortexweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConsoleController {

    @RequestMapping("/console-classify")
    public String showConsoleClassifyPage() {
        return "console-classify";
    }

    @RequestMapping("/console-train")
    public String showConsoleTrainPage() {
        return "console-train";
    }

    @RequestMapping("/console-classifiers")
    public String showConsoleClassifiersPage() {
        return "console-classifiers";
    }
}
