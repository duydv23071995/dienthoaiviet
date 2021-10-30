package com.example.dienthoaiviet.controller.admin;


import com.example.dienthoaiviet.dto.CategoryDto;
import com.example.dienthoaiviet.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("category")
public class CategoryControler {
    @Autowired
    ICategoryService categoryService;
    @GetMapping("")
    public ResponseEntity<?> getAllCategory(){
        return ResponseEntity.ok(categoryService.findAll());
    }
    @PostMapping
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryDto categoryDto , BindingResult result){
        System.out.println(categoryDto);
        if(result.hasErrors()){
            return  ResponseEntity.ok("false");
        }
        categoryDto.setStatus(true);
        categoryDto=categoryService.saveAndFlush(categoryDto);
        return ResponseEntity.ok(categoryDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable int id, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.ok("false");
        }
        categoryDto.setId(id);
        return ResponseEntity.ok(categoryService.saveAndFlush(categoryDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id){
        CategoryDto categoryDto = categoryService.findById(id);
        if(categoryDto== null){
            return ResponseEntity.ok("false");
        }
        categoryDto.setStatus(false);
        return ResponseEntity.ok(categoryService.saveAndFlush(categoryDto));
    }
}
