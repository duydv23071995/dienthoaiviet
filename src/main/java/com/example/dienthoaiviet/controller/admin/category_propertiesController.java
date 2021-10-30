package com.example.dienthoaiviet.controller.admin;

import com.example.dienthoaiviet.service.ICategoryService;
import com.example.dienthoaiviet.service.IPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("category-properties")
public class category_propertiesController {
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IPropertiesService propertiesService;
    @GetMapping
    public String getALlCategory_Properties(Model model){
        model.addAttribute("listCategory", categoryService.findAll());
        model.addAttribute("listProperties", propertiesService.findAll());
        return "admin/category/categorys";
    }
}
