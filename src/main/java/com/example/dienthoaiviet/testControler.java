package com.example.dienthoaiviet;

import com.example.dienthoaiviet.dto.CategoryDto;
import com.example.dienthoaiviet.dto.RoleDto;
import com.example.dienthoaiviet.dto.StaffDto;
import com.example.dienthoaiviet.service.IStaffService;
import com.example.dienthoaiviet.service.impl.CategoryService;
import com.example.dienthoaiviet.service.impl.RoleService;
import com.example.dienthoaiviet.service.impl.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;
import java.util.List;

@RestController
@RequestMapping("helo")
public class testControler {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private IStaffService staffService;
    @GetMapping("")
    public ResponseEntity<?> test(){
          List<RoleDto> roleDtos = roleService.findAll();
          RoleDto  roleDto = roleDtos.get(0);
//        List<CategoryDto> list = categoryService.findAll();
//        System.out.println(list.size());
//        List<RoleDto> list1 = roleService.findAll();
//        System.out.println(list1.size());
//        List<StaffDto> list2 = staffService.findAll() ;
//        System.out.println(list2.get(0).getRole().getName());
//        StaffDto staffDto = list2.get(0);
////        staffDto.setEmail("duy@gmail.com");
//        staffService.saveAndFlush(staffDto);
//        System.out.println("thanh cong");
//        StaffDto staffDto1 = staffService.login(10038,"123");
//        System.out.println(staffDto1);
        return ResponseEntity.ok(roleDto);
    }
    @GetMapping("User")
    public  String testUser(){
        return "admin/user/addUser";
    }
}
