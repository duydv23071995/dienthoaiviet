package com.example.dienthoaiviet.controller.admin;

import com.example.dienthoaiviet.dto.BillDto;
import com.example.dienthoaiviet.service.IBillService;
import com.example.dienthoaiviet.service.IStaffService;
import com.example.dienthoaiviet.service.ITop10ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HomeController {
    @Autowired
    private ITop10ProductService top10ProductService;
    @Autowired
    private IStaffService staffService;
    @Autowired
    private IBillService billService;
    @GetMapping("home")
    public String homePage(Model model){
        model.addAttribute("listTop10", top10ProductService.findAllBy());
        model.addAttribute("quantityStaff", staffService.findAll().size());
        List<BillDto> list = billService.findAll(0,1);
        if(list.isEmpty()){
            model.addAttribute("quantityBill",0);
        }else {
            model.addAttribute("quantityBill", list.size());
        }
        return "admin/home/content";
    }
    @GetMapping ("/*")
    public String allPath(){
        return "redirect:/home";
    }
}
