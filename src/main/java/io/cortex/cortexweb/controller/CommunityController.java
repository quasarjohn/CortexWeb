package io.cortex.cortexweb.controller;

import io.cortex.cortexweb.model.Answer;
import io.cortex.cortexweb.model.CommunityQuestion;
import io.cortex.cortexweb.model.User;
import io.cortex.cortexweb.service.CommunityQuestionService;
import io.cortex.cortexweb.service.UserService;
import io.cortex.cortexweb.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class CommunityController {
    private UserService userService;
    private CommunityQuestionService communityQuestionService;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private void setCommunityQuestionService(CommunityQuestionService communityQuestionService) {
        this.communityQuestionService = communityQuestionService;
    }

    private String user;
    private String email;
    private int reputationScore;

    @RequestMapping(value = "/community-questions", method = {RequestMethod.GET, RequestMethod.POST})
    public String showCommunityQuestionsPage(Model model, Principal principal) {
        model.addAttribute("currentUserInfo", userService.findUserByUsername(currentUser(principal)));
        model.addAttribute("questions", communityQuestionService.findAllQuestions());
        model.addAttribute("utils", new Utils());
        return "community-questions";
    }

    @RequestMapping("/community-questions-ask-question")
    public String showCommunityAskQuestionPage(Model model, Principal principal) {
        User u = userService.findUserByEmail(principal.getName());
        user = u.getUsername();
        email = u.getEmail();
        reputationScore = u.getReputationScore();
        System.out.println("USER: " + user);
        model.addAttribute("currentUserInfo", userService.findUserByUsername(currentUser(principal)));
        model.addAttribute("sender", user);
        model.addAttribute("senderEmail", email);
        model.addAttribute("senderReputationScore", reputationScore);
        model.addAttribute("questions", communityQuestionService.findAllQuestions());
        return "community-ask-question";
    }

    @RequestMapping("/question-{question_number}")
    public String showQuestionPage(Model model, Principal principal,
                                   @PathVariable Integer question_number) {
        model.addAttribute("currentUserInfo", userService.findUserByUsername(currentUser(principal)));
        model.addAttribute("question", communityQuestionService.findOne(question_number));
        model.addAttribute("utils", new Utils());

        return "community-view-question";
    }

    @RequestMapping("/community-users")
    public String showCommunityUsersPage(Model model, Principal principal) {
        model.addAttribute("currentUserInfo", userService.findUserByUsername(currentUser(principal)));
        model.addAttribute("users", userService.findAllUsers());
        return "community-users";
    }


    @MessageMapping("/new-question")
    @SendTo("/community/questions")
    public CommunityQuestion postQuestion(CommunityQuestion question, Principal principal) {
        String currentUser = principal.getName();
        User u = userService.findUserByEmail(currentUser);

        System.out.println(u.getImg_url() + "<- img_url" + currentUser + "<- currentUser");

        question.setImg_url(u.getImg_url());
        question.setPICTURE_PATH(u.getPICTURE_PATH());
        question.setTime_stamp(System.currentTimeMillis());
        question.setUsername(user);
        question.setQUESTION_NUMBER(communityQuestionService.showQuestionNumber() + 1);
//        question.setParsed_time(new Utils().parseEpochDate(question.getTime_stamp()));
        communityQuestionService.postQuestion(question);

        return question;
    }


    //TODO dynamic DestinationVariable
    @MessageMapping("/new-answer/question-{question_number}")
    @SendTo("/community/question-{question_number}")
    public Answer postComment(Answer comment, Principal principal,
                              @DestinationVariable String question_number) {

        System.out.println("new comment");
        comment.setTime_stamp(System.currentTimeMillis());
        comment.setParsed_time(new Utils().parseEpochDate(comment.getTime_stamp()));
        comment.setUsername(principal.getName());

        return comment;
    }

    private String currentUser(Principal principal) {
        User u = userService.findUserByEmail(principal.getName());
        String user = u.getUsername();

        return user;
    }

    /*@RequestMapping("/show/question")
    public String redirectToCommunityQuestions() {
        System.out.println("test test test");
        System.out.println("Redirecting..");
        return "redirect:/community-questions";
    }*/
}
