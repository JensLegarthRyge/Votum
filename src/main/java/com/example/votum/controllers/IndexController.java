package com.example.votum.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @GetMapping("/")
    public String frontPage() {
        return "index";
    }

    @GetMapping("/logged-in-frontpage")
    public String loggedInFrontpage(){
        return "frontPage";
    }

    @PostMapping("/login-user")
    public String loginInfoUser(WebRequest dataFromForm) {
        System.out.println(dataFromForm.getParameter("email-ting"));
        String email = dataFromForm.getParameter("email-ting");
        System.out.println(dataFromForm.getParameter("psw"));
        String password = dataFromForm.getParameter("psw");

        if (email != null && password!= null) {
            return "redirect:/logged-in-frontpage";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/create-user")
    public String createUserInfo(WebRequest dataFromForm) {
        System.out.println(dataFromForm.getParameter("create-email"));
        String email = dataFromForm.getParameter("create-email");

        System.out.println(dataFromForm.getParameter("create-psw"));
        String password = dataFromForm.getParameter("create-psw");

        System.out.println(dataFromForm.getParameter("birthday"));
        String birthday = dataFromForm.getParameter("birthday");

        System.out.println(dataFromForm.getParameter("sur-name"));
        String surName = dataFromForm.getParameter("sur-name");

        System.out.println(dataFromForm.getParameter("last-name"));
        String lastName = dataFromForm.getParameter("last-name");

        System.out.println(dataFromForm.getParameter("tlf-number"));
        int phoneNumber = Integer.parseInt(dataFromForm.getParameter("tlf-number"));

        return "redirect:/";

    }

    @GetMapping("/404-error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500";
            }
        }


        return "error";
    }



}
