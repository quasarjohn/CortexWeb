package io.cortex.cortexweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

    @RequestMapping("/")
    public String showIndexPage() {
        return "index";
    }

    @RequestMapping("/use-case")
    public String showUseCasesPage() {
        return "use-case";
    }

    @RequestMapping("/documentation")
    public String showDocumentationPage() {
        return "documentation";
    }

    @RequestMapping("/support")
    public String showSupportPage() {
        return "support";
    }

    @RequestMapping("/about")
    public String showAboutPage() {
        return "about";
    }

    @RequestMapping("/try-it")
    public String showTryItPage() {
        return "try-it";
    }
}
