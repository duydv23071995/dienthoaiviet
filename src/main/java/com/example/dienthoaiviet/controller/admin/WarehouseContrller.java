package com.example.dienthoaiviet.controller.admin;

import com.example.dienthoaiviet.service.IProduct_detailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("warehouse")
public class WarehouseContrller {
    @Autowired
    private IProduct_detailsService product_detailsService;
    @GetMapping
    public String findAll(Model model){
        model.addAttribute("listProductsWarehouse",product_detailsService.findAll());
        return "admin/warehouse/warehouses";
    }
}
