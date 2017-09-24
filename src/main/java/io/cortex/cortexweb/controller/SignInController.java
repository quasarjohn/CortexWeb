package io.cortex.cortexweb.controller;

import io.cortex.cortexweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class SignInController {

    @Autowired
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/sign-in")
    public String showSignInPage() {
        return "sign-in";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(Principal principal, HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        new SecurityContextLogoutHandler().logout(request, response, auth);

        return "redirect:/sign-in?logout";
    }


//    @RequestMapping("/index")   //Wala pang ui sa validation kaya sa console palang output
//    public String showCommunityOrConsoleIndex(HttpServletRequest httpServletRequest, User user, BindingResult bindingResult) {
//        String referrer = httpServletRequest.getHeader("referer");
//        System.out.println(referrer);
//        String serverName = httpServletRequest.getServerName();
//        System.out.println(serverName);
//
//        referrer = referrer.replace("http://"+serverName+":8090", "");
//        System.out.println(referrer);
//
//        User userEmail = userService.findUserByEmail(user.getEmail());
//        String userPassword;
//
//        if(userEmail != null) { //user email in db
//            userPassword = userEmail.getPassword();
//        }
//        else { //user email in db
//            System.out.println("Invalid username and password");
//            if(referrer.equals("/sign-in"))  //if this is equals to console page
//                return "redirect:/sign-in"; //console/sign-in
//            else
//                return "redirect:/community-sign-in"; //community/index
//        }
//
//        if(userEmail != null && userPassword.equals(user.getPassword())) { //validate user
//            System.out.println("Correct username and password" + userEmail + " " + userPassword);
//            if(referrer.equals("/sign-in"))  //if this is equals to console page
//                return "redirect:/console-overview"; //console/sign-in  //change into redirection type
//            else
//                return "/community-questions"; //community/index //change into redirection type
//        }
//        else {
//            System.out.println("Wrong username or password" + userEmail + " " + userPassword);
//            //back to referrers page
//            if(referrer.equals("/sign-in"))  //if this is equals to console page
//                return "redirect:/sign-in"; //console/sign-in
//            else
//                return "redirect:/community-sign-in"; //community/index
//        }
//    }
}
