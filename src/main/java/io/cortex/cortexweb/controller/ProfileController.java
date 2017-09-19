package io.cortex.cortexweb.controller;

import io.cortex.cortexweb.model.User;
import io.cortex.cortexweb.security.IAuthenticationManager;
import io.cortex.cortexweb.service.CommunityQuestionService;
import io.cortex.cortexweb.service.SocialService;
import io.cortex.cortexweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {
    private UserService userService;
    private CommunityQuestionService communityQuestionService;
    private SocialService socialService;
    private IAuthenticationManager authenticationManager;
    private String user;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCommunityQuestionService(CommunityQuestionService communityQuestionService) {
        this.communityQuestionService = communityQuestionService;
    }

    @Autowired
    public void setSocialService(SocialService socialService) {
        this.socialService = socialService;
    }

    @Autowired
    public void setAuthenticationManager(IAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping("/user-profile")
    public String showUserProfilePage(Model model) {
        user = authenticationManager.getCurrentUser();
        model.addAttribute("userInfo", userService.findUserByEmail(user));
        model.addAttribute("userQuestions", communityQuestionService.findAllUserQuestions(user));
        model.addAttribute("currentUser", user);
        model.addAttribute("followers", socialService.findAllUserFollowers(user));
        model.addAttribute("following", socialService.findAllUserFollowing(user));

        return "user-profile";
    }

    @RequestMapping("/user-settings")
    public String showUserSettingsPage(Model model) {
        user = authenticationManager.getCurrentUser();
        model.addAttribute("user", userService.findUserByEmail(user));

        return "user-settings";
    }

    @RequestMapping("update/profile")
    public String updateProfile(User userModel) {
        user = authenticationManager.getCurrentUser();
        userService.updateUser(userModel.getUsername(), userModel.getBio(), user);

        return "redirect:/user-profile";
    }

    @RequestMapping("unfollow/user/{followingEmail}")
    public String unfollowUser(@PathVariable String followingEmail) {
        user = authenticationManager.getCurrentUser();
        System.out.println(followingEmail + " <- following email -> " + user);
        socialService.unfollowUserFollowing(user, followingEmail+".com");

        return "redirect:/user-profile";
    }
}
