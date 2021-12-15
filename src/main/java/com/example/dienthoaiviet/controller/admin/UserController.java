package com.example.dienthoaiviet.controller.admin;

import com.example.dienthoaiviet.dto.RoleDto;
import com.example.dienthoaiviet.dto.StaffDto;
import com.example.dienthoaiviet.service.IRoleService;
import com.example.dienthoaiviet.service.IStaffService;
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

//
//@CrossOrigin(origins = "http://localhost:3006/")
//@RestController
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

    //
//    @GetMapping("")
//    public ResponseEntity<?> getUsers() {
//        List<StaffDto> list = staffService.findAll();
//        return ResponseEntity.ok(list);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getUserById(@PathVariable int id) {
//        StaffDto staffDto = staffService.findById(id);
//        if (staffDto == null) {
//            return ResponseEntity.ok("false");
//        }
//        return ResponseEntity.ok(staffDto);
//
//    }
//
//    @PostMapping("")
//    public ResponseEntity<?> addUser(@Valid @RequestBody StaffDto staffDto, BindingResult result) {
//        StringBuilder sb = new StringBuilder();
//        if (result.hasErrors()) {
//            return ResponseEntity.ok("false");
//        }
//        if (staffService.findByEmail(staffDto.getEmail()) != null) {
//            sb.append("trùng email");
//        }
//        if (staffService.findByPhone(staffDto.getPhoneNumber()) != null) {
//            sb.append("trúng số điện thoại");
//        }
//        if (sb.length() > 0) {
//            return ResponseEntity.ok(sb);
//        }
//        StaffDto staffDto1 = staffService.saveAndFlush(staffDto);
//        return ResponseEntity.ok(staffDto1);
//    }
//

    //
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteStaff(@PathVariable int id) {
//        StaffDto staffDto = staffService.findById(id);
//        if (staffDto == null) {
//            return ResponseEntity.ok("false");
//        }
//        staffDto.setStatus(false);
//        staffService.saveAndFlush(staffDto);
//        return ResponseEntity.ok("true");
//    }
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
        staffDto.setStatus(true);
        staffDto.setRole(roleService.getById(staffDto.getRole_id()));
        staffService.saveAndFlush(staffDto);
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

    //    @PostMapping
//    @ResponseBody
//    public ResponseEntity<?> addStaff(@Valid @RequestBody StaffDto staffDto, BindingResult result){
//        StringBuilder sb = new StringBuilder();
//        System.out.println(staffDto);
//        if (result.hasErrors()){
//            return ResponseEntity.ok("false");
//        }
//        if (staffService.findByEmail(staffDto.getEmail()) != null) {
//            sb.append("same email ");
//        }
//        if (staffService.findByPhone(staffDto.getPhoneNumber()) != null) {
//            sb.append("same phone ");
//        }
//        if (sb.length()>0){
//            return ResponseEntity.ok(sb.toString());
//        }
////        try {
////                Path path1 = Paths.get("src/main/resources/static/images/");
////                Files.copy(staffDto.getImg().getInputStream(),path1.resolve(staffDto.getImg().getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
////            }catch (Exception e){
////
////            }
////        staffDto.setStatus(true);
////        staffDto.setImage(staffDto.getImg().getOriginalFilename());
////        staffDto.setRole(roleService.getById(staffDto.getChucVu()));
////        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
////        try {
////            Date now = sdf.parse(staffDto.getBirthDay1());
////            staffDto.setBirthDay(now);
////        } catch (ParseException e) {
////            e.printStackTrace();
////        }
////        staffService.saveAndFlush(staffDto);
//        System.out.println(staffDto);
//        return ResponseEntity.ok(staffDto);
//    }
//    @PostMapping
//    @ResponseBody
//    public String addStaff(@Valid @RequestBody StaffDto staffDto, Model model, BindingResult result) throws IOException {
//        if (result.hasErrors()){
//            return "false";
//        }
//        System.out.println(staffDto);
//        if(!img.isEmpty()){
//            try {
//                Path path1 = Paths.get("src/main/resources/static/images/");
//                Files.copy(img.getInputStream(),path1.resolve(img.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        return "admin/user/users";
//    }

}
