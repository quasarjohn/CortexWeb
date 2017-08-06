package io.cortex.cortexweb.controller;

import io.cortex.cortexweb.model.User;
import io.cortex.cortexweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SignInController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({"/sign-in", "community-sign-in"})
    public String showSignInPage(Model model) {
        model.addAttribute("user", new User());
        return "sign-in";
    }

    @RequestMapping("/index")   //Wala pang ui sa validation kaya sa console palang output
    public String showCommunityOrConsoleIndex(HttpServletRequest httpServletRequest, User user, BindingResult bindingResult) {
        String referrer = httpServletRequest.getHeader("referer");
        System.out.println(referrer);
        String serverName = httpServletRequest.getServerName();
        System.out.println(serverName);

        referrer = referrer.replace("http://"+serverName+":8090", "");
        System.out.println(referrer);

        User userEmail = userService.findUserByEmail(user.getEmail());
        String userPassword;

        if(userEmail != null) { //user email in db
            userPassword = userEmail.getPassword();
        }
        else { //user email in db
            System.out.println("Invalid username and password");
            if(referrer.equals("/sign-in"))  //if this is equals to console page
                return "redirect:/sign-in"; //console/sign-in
            else
                return "redirect:/community-sign-in"; //community/index
        }

        if(userEmail != null && userPassword.equals(user.getPassword())) { //validate user
            System.out.println("Correct username and password" + userEmail + " " + userPassword);
            if(referrer.equals("/sign-in"))  //if this is equals to console page
                return "redirect:/console-overview"; //console/sign-in  //change into redirection type
            else
                return "/community-questions"; //community/index //change into redirection type
        }
        else {
            System.out.println("Wrong username or password" + userEmail + " " + userPassword);
            //back to referrers page
            if(referrer.equals("/sign-in"))  //if this is equals to console page
                return "redirect:/sign-in"; //console/sign-in
            else
                return "redirect:/community-sign-in"; //community/index
        }
    }
}
