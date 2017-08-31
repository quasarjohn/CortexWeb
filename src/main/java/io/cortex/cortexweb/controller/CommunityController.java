package io.cortex.cortexweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommunityController {

    @RequestMapping("/community-questions")
    public String showCommunityQuestionsPage() {
        return "community-questions";
    }

    @RequestMapping("/community-questions-ask-question")
    public String showCommunityAskQuestionPage() { return "community-ask-question"; }

    @RequestMapping("/question")
    public String showQuestionPage() { return "community-view-question"; }

    @RequestMapping("/community-users")
    public String showCommunityUsersPage() { return "community-users"; }
}
