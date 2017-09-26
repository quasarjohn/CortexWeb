package io.cortex.cortexweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForbiddenController {

    @RequestMapping("/forbidden")
    public String forbidden() {

        return "forbidden";
    }
}
