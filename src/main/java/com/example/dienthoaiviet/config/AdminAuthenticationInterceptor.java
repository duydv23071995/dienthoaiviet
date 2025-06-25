package com.example.dienthoaiviet.config;

import com.example.dienthoaiviet.dto.StaffDto;
import com.example.dienthoaiviet.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Component
public class AdminAuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(session.getAttribute("user")==null){
            response.sendRedirect("/login");
            return false;
        }
        StaffDto staffDto = (StaffDto) session.getAttribute("user");
//        int code = (int) Math.floor(((Math.random() * 899999) + 100000));
//        String content = "Mã Xác Nhận Là : " + code;
        String path = request.getServletPath();
        AntPathMatcher matcher = new AntPathMatcher();
        if(matcher.match("/users/**", path)){
            if(!staffDto.getRole().getName().equalsIgnoreCase("admin") && !staffDto.getRole().getName().equalsIgnoreCase("Quản Lí")){
                response.sendRedirect("/home");
                return false;
            }
        }
        return true;
    }
}
