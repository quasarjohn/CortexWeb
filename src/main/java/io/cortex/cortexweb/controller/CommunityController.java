package io.cortex.cortexweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommunityController {

    @RequestMapping("/community-questions")
    public String showCommunityQuestions() {
        return "community-questions";
    }

    @RequestMapping("/community-users")
    public String showCommunityUsers() { return "community-users"; }
}
