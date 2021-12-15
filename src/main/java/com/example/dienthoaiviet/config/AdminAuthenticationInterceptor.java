//package com.example.dienthoaiviet.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//@Component
//public class AdminAuthenticationInterceptor implements HandlerInterceptor {
//    @Autowired
//    private HttpSession session;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if(session.getAttribute("user")==null){
//            response.sendRedirect("/login");
//            return false;
//        }
//        return true;
//    }
//}
