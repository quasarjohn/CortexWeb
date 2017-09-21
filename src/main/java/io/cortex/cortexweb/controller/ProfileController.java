package io.cortex.cortexweb.controller;

import io.cortex.cortexweb.model.Social;
import io.cortex.cortexweb.model.User;
import io.cortex.cortexweb.security.IAuthenticationManager;
import io.cortex.cortexweb.service.CommunityQuestionService;
import io.cortex.cortexweb.service.SocialService;
import io.cortex.cortexweb.service.UserService;
import io.cortex.cortexweb.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    private UserService userService;
    private CommunityQuestionService communityQuestionService;
    private SocialService socialService;
    private IAuthenticationManager authenticationManager;
    private String currentUser;
    private String uri;

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

    @RequestMapping("{otherUser}-profile")
    public String showUserProfilePage(Model model, @PathVariable String otherUser, HttpServletRequest httpServletRequest) {
        currentUser = authenticationManager.getCurrentUser();
        System.out.println(currentUser + " and " + otherUser);
        uri = httpServletRequest.getRequestURI();
        System.out.println(uri);

        if (currentUser.equals(otherUser)) {
            profiling(model, currentUser, currentUser);
        } else {
            profiling(model, otherUser, currentUser);
        }

        return "user-profile";
    }

    @RequestMapping("/user-settings")
    public String showUserSettingsPage(Model model) {
        currentUser = authenticationManager.getCurrentUser();
        model.addAttribute("user", userService.findUserByEmail(currentUser));

        return "user-settings";
    }

    @RequestMapping("update/profile")
    public String updateProfile(User userModel) {
        currentUser = authenticationManager.getCurrentUser();
        userService.updateUser(userModel.getUsername(), userModel.getBio(), currentUser);

        return "redirect:/" + currentUser + "-profile";
    }

    @RequestMapping("follow/user/{userEmail}/{userReputationScore}/{userUsername}/{otherUserEmail}/{otherUserReputationScore}/{otherUserUsername}")
    public String followUser(@PathVariable String userEmail, @PathVariable int userReputationScore, @PathVariable String userUsername,
                             @PathVariable String otherUserEmail, @PathVariable int otherUserReputationScore, @PathVariable String otherUserUsername) {
        currentUser = authenticationManager.getCurrentUser();
        System.out.println(userEmail + " <- following email -> " + currentUser);
        System.out.println(userEmail + " " + userReputationScore + " " + userUsername + " " + otherUserEmail
                + " " + otherUserReputationScore + " " + otherUserUsername);
        socialService.followUser(userEmail, userReputationScore, userUsername, otherUserEmail, otherUserReputationScore, otherUserUsername);
        System.out.println(uri + " unfollow/user/{followingEmail} mapping");
        String currentUserPathname = "/" + currentUser + "-profile";

        if (currentUserPathname.equals(uri)) {
            return "redirect:/" + currentUser + "-profile";
        } else {
            return "redirect:" + uri;
        }
    }

    @RequestMapping("unfollow/user/{userEmail}")
    public String unfollowUser(@PathVariable String userEmail) {
        currentUser = authenticationManager.getCurrentUser();
        System.out.println(userEmail + " <- following email -> " + currentUser);
        socialService.unfollowUser(currentUser, userEmail + ".com");
        System.out.println(uri + " unfollow/user/{followingEmail} mapping");
        String currentUserPathname = "/" + currentUser + "-profile";

        if (currentUserPathname.equals(uri)) {
            return "redirect:/" + currentUser + "-profile";
        } else {
            return "redirect:" + uri;
        }
    }

    private void profiling(Model model, String user, String currentUser) {
        model.addAttribute("userInfo", userService.findUserByEmail(user));
        model.addAttribute("currentUserInfo", userService.findUserByEmail(currentUser));
        model.addAttribute("userQuestions", communityQuestionService.findAllUserQuestions(user));
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("followers", socialService.findAllUserFollowers(user));
        model.addAttribute("following", socialService.findAllUserFollowing(user));
        model.addAttribute("followingList", socialService.checkUserFollowing(currentUser, user));
        model.addAttribute("socialTable", socialService.checkSocialTableSize());
        model.addAttribute("utils", new Utils());
    }
}
