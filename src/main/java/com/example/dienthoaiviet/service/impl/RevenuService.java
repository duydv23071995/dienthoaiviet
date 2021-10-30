//package com.example.dienthoaiviet.service.impl;
//
//import com.example.dienthoaiviet.entity.Revenue;
//import com.example.dienthoaiviet.jpaRepository.RevenueRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class RevenuService {
//    @Autowired
//    private RevenueRepository revenueRepository;
//
//    public List<Revenue> findAll() {
//        String day = "CONVERT(o.day,Date)";
//        return revenueRepository.findAll(day);
//    }
//}
