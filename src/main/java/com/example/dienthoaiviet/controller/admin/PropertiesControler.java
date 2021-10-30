package com.example.dienthoaiviet.controller.admin;

import com.example.dienthoaiviet.dto.PropertiesDto;
import com.example.dienthoaiviet.service.IPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("properties")
public class PropertiesControler {
    @Autowired
    private IPropertiesService propertiesService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(propertiesService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> addProperties(@Valid @RequestBody PropertiesDto propertiesDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.ok("false");
        }
        propertiesDto.setStatus(true);
        return ResponseEntity.ok(propertiesService.saveAndFlush(propertiesDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProperties(@Valid @RequestBody PropertiesDto propertiesDto, @PathVariable int id, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.ok("false");
        }
        propertiesDto.setId(id);
        return ResponseEntity.ok(propertiesService.saveAndFlush(propertiesDto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProperties(@PathVariable int id) {
        PropertiesDto propertiesDto = propertiesService.findById(id);
        propertiesDto.setStatus(false);
        return ResponseEntity.ok(propertiesService.saveAndFlush(propertiesDto));
    }
}
