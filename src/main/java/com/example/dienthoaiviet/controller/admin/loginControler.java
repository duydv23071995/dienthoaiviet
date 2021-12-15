package com.example.dienthoaiviet.controller.admin;

import com.example.dienthoaiviet.dto.LoginDto;
import com.example.dienthoaiviet.dto.StaffDto;
import com.example.dienthoaiviet.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("login")
public class loginControler {
    @Autowired
    IStaffService staffService;
    @Autowired
    HttpSession session;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    @GetMapping("")
    public String login(Model userLogin){
        Cookie[] cookie = request.getCookies();
        LoginDto user = new LoginDto();
        for (Cookie x: cookie) {
            if(x.getName().equals("username")){
                if(!x.getValue().equals("")) {
                    user.setId(Integer.parseInt(x.getValue()));
                }
            }
            if(x.getName().equals("password")){
                user.setPassword(x.getValue());
            }
        }
        if(user.getId()!=null){
            user.setRemember(true);
        }else {
            user.setRemember(false);
        }
        userLogin.addAttribute("user",user);
        return "login/login";
    }
    @PostMapping("/oke")
    @ResponseBody
    public boolean checkLogin(@Valid @RequestBody LoginDto user , BindingResult result){
        if(result.hasErrors()){
            return false;
        }
        StaffDto staff = staffService.login(user.getId(), user.getPassword());
        if(staff!=null){
            session.setAttribute("user",staff);
            if(user.isRemember()){
                response.addCookie(new Cookie("username",String.valueOf(user.getId())));
                response.addCookie(new Cookie("password",user.getPassword()));
            }else {
                response.addCookie(new Cookie("username",""));
                response.addCookie(new Cookie("password",""));
            }
            return true;
        }
        return false;
    }
    @PostMapping("/email/{email}")
    @ResponseBody
    public  ResponseEntity<?> loginEmail(@PathVariable String email){
        StaffDto staffDto = staffService.findByEmail(email);
        if(staffDto==null){
            return  ResponseEntity.ok(false);
        }
        session.setAttribute("user",staffDto);
        return ResponseEntity.ok(true);
    }

}
