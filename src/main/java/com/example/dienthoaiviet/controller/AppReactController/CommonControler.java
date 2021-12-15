package com.example.dienthoaiviet.controller.AppReactController;

import com.example.dienthoaiviet.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author: GMO_DuyDV
 * @version: 1.0
 * @since: 12/11/2021
 * Project_name: GMO_QuanLyTaiSan
 */
@RestController
@CrossOrigin("http://localhost:3000/")
public class CommonControler {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("/didongviet/category")
    public ResponseEntity<?> getAllCategory(){
     return ResponseEntity.ok(categoryService.findAll());
    }
}
