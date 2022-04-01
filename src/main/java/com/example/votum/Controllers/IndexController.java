package com.example.votum.Controllers;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class IndexController {
    @GetMapping("/")
    public static String frontPage() {
        return "index";
    }

    @GetMapping("/cookiepolitik")
    public static String cookiepolitik() {
        return "cookiepolitik";
    }


}
