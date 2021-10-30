package com.example.dienthoaiviet.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class LogoutController {
    @Autowired
    private HttpSession session;
    @GetMapping
    public String logout(){
        session.removeAttribute("user");
        return "redirect:/login";
    }
}
