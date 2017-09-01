package io.cortex.cortexweb.controller;

import io.cortex.cortexweb.model.User;
import io.cortex.cortexweb.security.AuthenticationManager;
import io.cortex.cortexweb.security.IAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PageController {

    @Autowired
    private AuthenticationManager authenticationManager;

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


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = authenticationManager.getSecurityContext().getAuthentication();
        if (auth != null) {
            System.out.println("HINDI NULL");
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/sign-in?logout";
    }
}
