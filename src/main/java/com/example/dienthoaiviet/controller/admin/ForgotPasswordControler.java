package com.example.dienthoaiviet.controller.admin;

import com.example.dienthoaiviet.dto.MailDto;
import com.example.dienthoaiviet.dto.StaffDto;
import com.example.dienthoaiviet.entity.OtpCodeEntity;
import com.example.dienthoaiviet.entity.Staff;
import com.example.dienthoaiviet.jpaRepository.IOtpCodeRepository;
import com.example.dienthoaiviet.jpaRepository.StaffRepository;
import com.example.dienthoaiviet.service.IStaffService;
import com.example.dienthoaiviet.service.impl.CookieService;
import com.example.dienthoaiviet.service.impl.MailService;
import com.example.dienthoaiviet.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;


@Controller
@RequestMapping("forgot-password")
public class ForgotPasswordControler {
    @Autowired
    private IStaffService staffService;
    @Autowired
    private MailService mailService;
    @Autowired
    private CookieService cookieService;
    @Autowired
    private IOtpCodeRepository otpCodeRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
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

        OtpCodeEntity otp = new OtpCodeEntity();
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MINUTE, 5); // +5 phút
        otp.setExp_time(calendar.getTime());
        otp.setOtp(""+code);
        otp.setCreateDate(now);
        otp.setUseBy(email.getTo());
        otpCodeRepository.saveAndFlush(otp);
        return ResponseEntity.ok(true);
    }
    @PostMapping("/code")
    public ResponseEntity<?> checkCode(@RequestBody MailDto mailDto){
        String passwordNew = PasswordGenerator.generatePassword();
        OtpCodeEntity otp = otpCodeRepository.findFirstByOtpAndUseBy(String.valueOf(mailDto.getCode()), mailDto.getTo());
        if(otp != null && otp.getExp_time().after(new Date())){
            String content = "Mật khẩu mới Là : " +passwordNew;
            mailDto.setBody(content);
            mailDto.setSubject("Lấy lại mật khẩu");
            mailService.sendMail(mailDto);
            Staff staff =  staffRepository.findByEmail(mailDto.getTo()).get();
            staff.setPassword(bCryptPasswordEncoder.encode(passwordNew));
            staffRepository.saveAndFlush(staff);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }
}
