package com.example.dienthoaiviet.controller.admin;

import com.example.dienthoaiviet.dto.CustomerDto;
import com.example.dienthoaiviet.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("customer")
public class CustomerControler {
    @Autowired
    ICustomerService customerService;
    @GetMapping
    public String findALl(Model model){
        model.addAttribute("listCustomer", customerService.findAll());
        return "admin/customer/customer";
    }
    @PostMapping("")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerDto customerDto, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.ok("false");
        }
        customerDto.setStatus(true);
        return ResponseEntity.ok(customerService.saveAndFlush(customerDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody CustomerDto customerDto,@PathVariable  int id, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.ok("false");
        }
        customerDto.setId(id);
        return ResponseEntity.ok(customerService.saveAndFlush(customerDto));
    }
    @DeleteMapping("{id}")
    public ResponseEntity deleteCustomer(@PathVariable int id){
        CustomerDto customerDto = customerService.findById(id);
        return ResponseEntity.ok(customerDto);
    }
}
