package com.example.booktest.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {
    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        SecurityContextHolder.clearContext();
        request.getSession().invalidate();

        return "redirect:/login";
    }
}
