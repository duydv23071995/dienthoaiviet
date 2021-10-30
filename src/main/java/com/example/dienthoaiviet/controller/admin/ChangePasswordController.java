package com.example.dienthoaiviet.controller.admin;

import com.example.dienthoaiviet.dto.ChangepassworDto;
import com.example.dienthoaiviet.dto.LoginDto;
import com.example.dienthoaiviet.dto.StaffDto;
import com.example.dienthoaiviet.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/changepassword")
public class ChangePasswordController {
    @Autowired
    private HttpSession session;
    @Autowired
    private IStaffService staffService;
    @GetMapping
    public String getChangePassword(){
        return "admin/changepassword";
    }
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> addChangePass(@RequestBody ChangepassworDto changepassworDto){
        StaffDto staffDto = (StaffDto) session.getAttribute("user");
        staffDto = staffService.login(staffDto.getId(),changepassworDto.getPass());
        if(staffDto==null){
            return ResponseEntity.ok(false);
        }else {
            staffDto.setPassword(changepassworDto.getPass1());
            staffService.saveAndFlush(staffDto);
            return ResponseEntity.ok(true);
        }
    }
}
