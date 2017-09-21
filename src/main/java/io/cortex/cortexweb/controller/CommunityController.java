package io.cortex.cortexweb.controller;

import io.cortex.cortexweb.model.CommunityQuestion;
import io.cortex.cortexweb.security.IAuthenticationManager;
import io.cortex.cortexweb.service.CommunityQuestionService;
import io.cortex.cortexweb.service.UserService;
import io.cortex.cortexweb.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommunityController {
    private UserService userService;
    private CommunityQuestionService communityQuestionService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCommunityQuestionService(CommunityQuestionService communityQuestionService) {
        this.communityQuestionService = communityQuestionService;
    }

    @Autowired
    private IAuthenticationManager authenticationManager;
    private String user;

    @RequestMapping(value = "/community-questions", method = {RequestMethod.GET, RequestMethod.POST})
    public String showCommunityQuestionsPage(Model model) {
        model.addAttribute("questions", communityQuestionService.findAllQuestions());
        model.addAttribute("utils", new Utils());
        return "community-questions";
    }

    @RequestMapping("/community-questions-ask-question")
    public String showCommunityAskQuestionPage(Model model) {
        user = authenticationManager.getCurrentUser();
        model.addAttribute("sender", user);
        model.addAttribute("questions", communityQuestionService.findAllQuestions());
        return "community-ask-question";
    }

    @RequestMapping("/question")
    public String showQuestionPage() {
        return "community-view-question";
    }

    @RequestMapping("/community-users")
    public String showCommunityUsersPage(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "community-users";
    }


    @MessageMapping("/new-question")
    @SendTo("/community/questions")
    public CommunityQuestion postQuestion(CommunityQuestion question) {
        question.setTime_stamp(System.currentTimeMillis());
        question.setUser_id(user);
        question.setQUESTION_NUMBER(communityQuestionService.showQuestionNumber() + 1);
        communityQuestionService.postQuestion(question);

        return question;
    }

    /*@RequestMapping("/show/question")
    public String redirectToCommunityQuestions() {
        System.out.println("test test test");
        System.out.println("Redirecting..");
        return "redirect:/community-questions";
    }*/
}
