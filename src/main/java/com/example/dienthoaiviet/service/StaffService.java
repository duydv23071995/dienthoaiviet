//package com.example.dienthoaiviet.service;
//
//import com.example.dienthoaiviet.entity.Staff;
//import com.example.dienthoaiviet.jpaRepository.StaffRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class StaffService implements UserDetailsService {
//
//    @Autowired
//    private StaffRepository staffRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Staff staff = staffRepository.findFirstById(Integer.parseInt(userName));
//        if(staff == null){
//            throw new
//        }
//        return null;
//    }
//}
