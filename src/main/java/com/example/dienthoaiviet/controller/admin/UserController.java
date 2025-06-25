package com.example.dienthoaiviet.controller.admin;

import com.example.dienthoaiviet.dto.MailDto;
import com.example.dienthoaiviet.dto.RoleDto;
import com.example.dienthoaiviet.dto.StaffDto;
import com.example.dienthoaiviet.service.IRoleService;
import com.example.dienthoaiviet.service.IStaffService;
import com.example.dienthoaiviet.service.impl.MailService;
import com.example.dienthoaiviet.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IStaffService staffService;
    @Autowired
    IRoleService roleService;
    @Autowired
    ServletContext application;
    @Autowired
    private HttpSession session;
    @Autowired
    private MailService mailService;

    @PutMapping
    @ResponseBody
    public ResponseEntity<?> updateStaff(@Valid @RequestBody StaffDto staffDto, BindingResult result) {
        System.out.println(staffDto);
        if (result.hasErrors()) {
            ResponseEntity.ok("false");
        }
        if (staffService.findById(staffDto.getId()) == null) {
            return ResponseEntity.ok("id does not exist");
        }
        staffDto.setStatus(true);
        staffDto.setRole(roleService.getById(staffDto.getRole_id()));
        staffService.saveAndFlush(staffDto);
        return ResponseEntity.ok(staffDto);
    }

    @GetMapping("")
    public String getALL(Model model) {
        model.addAttribute("listUsers", staffService.findAll());
        return "admin/user/users";
    }

    @GetMapping("/themnhanvien")
    public String addStaffIndex(Model model) {

        return "admin/user/addUser";
    }
    @GetMapping("/{id}")
    public String getStaffId(Model model,@PathVariable int id){
        StaffDto staffDto =staffService.findById(id);
        if(staffDto==null){
            return "redirect:/users";
        }
        model.addAttribute("staff",staffDto);
        return "admin/user/editUser";
    }
    @ModelAttribute("listRole")
    public List<RoleDto> getALlRole() {
        return roleService.findAll();
    }
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> add( @Valid @RequestBody StaffDto staffDto,BindingResult result){
        StringBuilder sb = new StringBuilder();
        if (result.hasErrors()){
            return ResponseEntity.ok("false");
        }
        if (staffService.findByEmail(staffDto.getEmail()) != null) {
            sb.append("same email ");
        }
        if (staffService.findByPhone(staffDto.getPhoneNumber()) != null) {
            sb.append("same phone ");
        }
        if (sb.length()>0){
            return ResponseEntity.ok(sb.toString());
        }
        String password= PasswordGenerator.generatePassword();
        staffDto.setPassword(password);
        staffDto.setStatus(true);
        staffDto.setRole(roleService.getById(staffDto.getRole_id()));
        staffDto= staffService.saveAndFlush(staffDto);
        MailDto mailDto = new MailDto();
        String content = "User name login là :  " + staffDto.getId() + ", Mật khẩu đăng nhập là : "+password ;
        mailDto.setBody(content);
        mailDto.setSubject("Cấp user mới");
        mailDto.setTo(staffDto.getEmail());
        mailService.sendMail(mailDto);
        return ResponseEntity.ok(staffDto);
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteStaff(@PathVariable int id,Model model) {
        StaffDto staffDto = staffService.findById(id);
        if(staffDto==null){
            return ResponseEntity.ok("Not staff");
        }
        staffDto.setStatus(false);
        staffService.saveAndFlush(staffDto);
        return ResponseEntity.ok(staffDto);
    }

}
