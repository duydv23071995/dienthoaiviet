package com.example.dienthoaiviet.controller.admin;

import com.example.dienthoaiviet.service.IVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private IVoucherService voucherService;
    @GetMapping
    public String getALl(Model model){
        model.addAttribute("listVoucher",voucherService.findAll());
        return "admin/voucher/voucher";
    }
}
