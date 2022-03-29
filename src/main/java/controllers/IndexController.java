package controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {
    @GetMapping("/")
    public static String frontPage() {
        return "index";
    }

}
