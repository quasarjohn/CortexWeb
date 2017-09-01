package io.cortex.cortexweb.controller;

import io.cortex.cortexweb.model.CommunityQuestion;
import io.cortex.cortexweb.security.AuthenticationManager;
import io.cortex.cortexweb.security.IAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommunityController {

    @Autowired
    private IAuthenticationManager authenticationManager;
    private String user;

    @RequestMapping("/community-questions")
    public String showCommunityQuestionsPage() {
        return "community-questions";
    }

    @RequestMapping("/community-questions-ask-question")
    public String showCommunityAskQuestionPage() {
        user = authenticationManager.getCurrentUser();
        return "community-ask-question";
    }

    @RequestMapping("/question")
    public String showQuestionPage() {
        return "community-view-question";
    }

    @RequestMapping("/community-users")
    public String showCommunityUsersPage() {
        return "community-users";
    }


    @MessageMapping("/new-question")
    @SendTo("/community/questions")
    public CommunityQuestion postQuestion(CommunityQuestion question) {
        question.setTime_stamp(System.currentTimeMillis());
        question.setUser_id(user);
        return question;
    }
}
