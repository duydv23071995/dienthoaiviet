package com.example.dienthoaiviet.controller.AppReactController;

import com.example.dienthoaiviet.service.IProductservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/didongviet")
@CrossOrigin("http://localhost:3000/")
public class HomePageAppController {
    @Autowired
    private IProductservice productservice;
    @GetMapping("/iphone")
    public ResponseEntity<?> getIphone(){

        return ResponseEntity.ok(productservice.findAllByIdCategory(2));
    }
    @GetMapping("/samsung")
    public ResponseEntity<?> getSamSung(){
        return ResponseEntity.ok(productservice.findAllByIdCategory(1));
    }
    @GetMapping("/other")
    public ResponseEntity<?> getOther(){
        return ResponseEntity.ok(productservice.findByIdCategoryOther());
    }
    @GetMapping("/hostsale")
    public ResponseEntity<?> getHostSale(){
        return ResponseEntity.ok(productservice.findHotsale());
    }

}
