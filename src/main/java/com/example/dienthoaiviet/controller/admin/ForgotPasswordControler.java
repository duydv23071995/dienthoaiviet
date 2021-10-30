package com.example.dienthoaiviet.controller.admin;

import com.example.dienthoaiviet.dto.MailDto;
import com.example.dienthoaiviet.dto.StaffDto;
import com.example.dienthoaiviet.service.IStaffService;
import com.example.dienthoaiviet.service.impl.CookieService;
import com.example.dienthoaiviet.service.impl.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("forgot-password")
public class ForgotPasswordControler {
    @Autowired
    private IStaffService staffService;
    @Autowired
    private MailService mailService;
    @Autowired
    private CookieService cookieService;
    @GetMapping
    public  String fogotPassword(){
        return "login/forgot-password";
    }
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> checkEmail(@RequestBody MailDto email) {
        StaffDto staffDto = staffService.findByEmail(email.getTo());
        if(staffDto == null){
            return ResponseEntity.ok(false);
        }
        int code = (int) Math.floor(((Math.random() * 899999) + 100000));
        String content = "Mã Xác Nhận Là : " + code;
        email.setBody(content);
        email.setSubject("Lấy lại mật khẩu");
        mailService.sendMail(email);
        cookieService.create("confimcode",""+code,1);
//        cookieService.create("emailConfim",email.getTo(),1);
        return ResponseEntity.ok(true);
    }
    @PostMapping("/code")
    public ResponseEntity<?> checkCode(@RequestBody MailDto mailDto){
        int code = Integer.parseInt( cookieService.get("confimcode"));
        if(code==mailDto.getCode()){
            String content = "Mật khẩu mới Là : 123";
            mailDto.setBody(content);
            mailDto.setSubject("Lấy lại mật khẩu");
            mailService.sendMail(mailDto);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }
}
