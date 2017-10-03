package io.cortex.cortexweb.controller;

import io.cortex.cortexweb.model.User;
import io.cortex.cortexweb.service.CommunityQuestionService;
import io.cortex.cortexweb.service.SocialService;
import io.cortex.cortexweb.service.UserService;
import io.cortex.cortexweb.utils.Utils;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
public class ProfileController {
    private UserService userService;
    private CommunityQuestionService communityQuestionService;
    private SocialService socialService;
    private String currentUser;
    private String uri;
    private static String DIR = "C://Users//JL//Desktop//Test//";

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


    @RequestMapping("{otherUser}-profile")
    public String showUserProfilePage(Model model, Principal principal, @PathVariable String otherUser, HttpServletRequest httpServletRequest) {
        currentUser = principal.getName();
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
    public String showUserSettingsPage(Model model, Principal principal) {
        currentUser = principal.getName();
        model.addAttribute("currentUserInfo", userService.findUserByUsername(currentUser(principal)));
        model.addAttribute("user", userService.findUserByEmail(currentUser));

        return "user-settings";
    }

    @PostMapping("update/profile")
    public String updateProfile(User user, Principal principal) {
        currentUser = principal.getName();

//        if (profilePicture.isEmpty()) {
//            System.out.println("File is empty");
//            return "redirect:/" + currentUser + "-profile";
//        }
//
//        File PROFILE_PICTURES_DIRECTORY = new File(DIR);
//
//        if(!PROFILE_PICTURES_DIRECTORY.exists()) {
//            PROFILE_PICTURES_DIRECTORY.mkdir();
//        }

        //DIR = DIR + currentUser + "//";
//        File USER_DIRECTORY = new File(returnPath(principal, DIR));
//
//        System.out.println(USER_DIRECTORY + " <- user_directory");
//        if(!USER_DIRECTORY.exists()) {
//            USER_DIRECTORY.mkdir();
//        }
//
//        try {
//            byte[] bytes = profilePicture.getBytes();
//            Path path = Paths.get(USER_DIRECTORY + "//" + currentUser + ".png");
//            Files.write(path, bytes);
//            System.out.println("Sent to server");
//            String PICTURE_PATH = "pictures/" + currentUser + ".png";
            userService.updateUser(user.getUsername(), user.getBio(), currentUser);
            socialService.changeUsernameFollowing(user.getUsername(), currentUser);
            socialService.changeUsernameFollower(user.getUsername(), currentUser);
            communityQuestionService.changeUsername(user.getUsername(), currentUser);
            System.out.println("User profile updated");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return "redirect:/" + currentUser + "-profile";
    }


    @RequestMapping("follow/user/{userEmail}/{userReputationScore}/{userUsername}/{otherUserEmail}/{otherUserReputationScore}/{otherUserUsername}")
    public String followUser(@PathVariable String userEmail, @PathVariable int userReputationScore, @PathVariable String userUsername,
                             @PathVariable String otherUserEmail, @PathVariable int otherUserReputationScore, @PathVariable String otherUserUsername,
                             Principal principal) {
        currentUser = principal.getName();
        System.out.println(userEmail + " <- following email -> " + currentUser);

        User user = userService.findUserByEmail(userEmail);
        User user1 = userService.findUserByEmail(otherUserEmail);

        System.out.println(otherUserUsername + "<- other user username");
        socialService.followUser(userEmail, userReputationScore, userUsername, user.getImg_url(), user.getPICTURE_PATH(),
                otherUserEmail, otherUserReputationScore, otherUserUsername, user1.getImg_url(), user1.getPICTURE_PATH());

        String currentUserPathname = "/" + currentUser + "-profile";

        if (currentUserPathname.equals(uri)) {
            return "redirect:/" + currentUser + "-profile";
        } else {
            return "redirect:" + uri;
        }
    }

    @RequestMapping("unfollow/user/{userEmail}")
    public String unfollowUser(Principal principal, @PathVariable String userEmail) {
        currentUser = principal.getName();
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

    private String currentUser(Principal principal) {
        User u = userService.findUserByEmail(principal.getName());
        String user = u.getUsername();

        return user;
    }

//    private String returnPath(Principal principal, String DIR) {
//        currentUser = principal.getName();
//        DIR = DIR + currentUser + "//";
//
//        return DIR;
//    }

    @GetMapping(value = "/get-image/server/{otherUser}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImageFromServer(Principal principal, @PathVariable String otherUser) throws IOException{
        currentUser = principal.getName();
        //get the file path in the db
        System.out.println(otherUser + ".com" + "<- from get-image/server mapping");
        otherUser = otherUser + ".com";
        User user;
        if(currentUser.equals(otherUser)) {
            user = userService.findUserByEmail(currentUser);
        } else {
            user = userService.findUserByEmail(otherUser);
        }
        String FILE_PATH = user.getPICTURE_PATH();

        System.out.println(FILE_PATH + " <- FILE_PATH from db");
        InputStream in = new BufferedInputStream(new FileInputStream(FILE_PATH));

        return IOUtils.toByteArray(in);
    }

    @PostMapping("/upload/image")
    public String uploadImage(@RequestParam("file") MultipartFile profilePicture, Principal principal) {
        currentUser = principal.getName();

        if (profilePicture.isEmpty()) {
            System.out.println("File is empty");
            return "redirect:/" + currentUser + "-profile";
        }

        File PROFILE_PICTURES_DIRECTORY = new File(DIR);

        if(!PROFILE_PICTURES_DIRECTORY.exists()) {
            PROFILE_PICTURES_DIRECTORY.mkdir();
        }

        //DIR = DIR + currentUser + "//";
        File USER_DIRECTORY = new File(DIR + currentUser + "//");

        System.out.println(USER_DIRECTORY + " <- user_directory");
        if(!USER_DIRECTORY.exists()) {
            USER_DIRECTORY.mkdir();
        }

        try {
            byte[] bytes = profilePicture.getBytes();
            Path path = Paths.get(USER_DIRECTORY + "//" + currentUser + ".png");
            Files.write(path, bytes);
            System.out.println("Sent to server");
            //save the path to db
            userService.changePicture(path.toString(), currentUser);
            socialService.changePictureFollowing(path.toString(), currentUser);
            socialService.changePictureFollower(path.toString(), currentUser);
            communityQuestionService.changePicture(path.toString(), currentUser);
            System.out.println("User profile updated");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/" + currentUser + "-profile";
    }

//    @PostMapping("/uploadPicture")
//    public String singleFileUpload(@RequestParam("file") MultipartFile profilePicture, Principal principal) {
//        currentUser = principal.getName();
//
//        if (profilePicture.isEmpty()) {
//            System.out.println("File is empty");
//            return "redirect:/" + currentUser + "-profile";
//        }
//
//        File PROFILE_PICTURES_DIRECTORY = new File(DIR);
//
//        if(!PROFILE_PICTURES_DIRECTORY.exists()) {
//            PROFILE_PICTURES_DIRECTORY.mkdir();
//        }
//
//        DIR = DIR + currentUser + "//";
//        File USER_DIRECTORY = new File(DIR);
//
//        if(!USER_DIRECTORY.exists()) {
//            USER_DIRECTORY.mkdir();
//        }
//
//        try {
//            byte[] bytes = profilePicture.getBytes();
//            Path path = Paths.get(DIR + "profilePicture.png");
//            Files.write(path, bytes);
//
//            System.out.println("Sent to server");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "redirect:/" + currentUser + "-profile";
//    }
}
