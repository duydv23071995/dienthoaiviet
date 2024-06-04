package com.example.dienthoaiviet.service.impl;

import com.example.dienthoaiviet.dto.StaffDto;
import com.example.dienthoaiviet.entity.Staff;
import com.example.dienthoaiviet.jpaRepository.StaffRepository;
import com.example.dienthoaiviet.service.IStaffService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService implements IStaffService {
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public List<StaffDto> findAll() {
           List<StaffDto> staffDtoList = new ArrayList<>();
           for(Staff staff :staffRepository.findAll()){
               staffDtoList.add(modelMapper.map(staff,StaffDto.class));
           }

        return staffDtoList;
    }

    @Override
    public <S extends Staff> List<S> saveAll(Iterable<S> iterable) {
        return staffRepository.saveAll(iterable);
    }

    @Override
    public StaffDto findByPhone(String phone) {
        Optional<Staff> staff =  staffRepository.findByPhone(phone);
        return staff.isEmpty() ? null : modelMapper.map(staff.get(),StaffDto.class);
    }

    @Override
    public StaffDto findByEmail(String email) {
        Optional<Staff> staff =  staffRepository.findByEmail(email);
        return staff.isEmpty() ? null : modelMapper.map(staff.get(),StaffDto.class);
    }

    @Override
    public  StaffDto saveAndFlush(StaffDto s) {
        StaffDto staffDto = findById(s.getId());
        if(staffDto==null){
            s.setPassword(bCryptPasswordEncoder.encode(s.getPassword()));
        }else {
            if(s.getPassword()==null){
                s.setPassword(staffDto.getPassword());
            }else {
                s.setPassword(bCryptPasswordEncoder.encode(s.getPassword()));
            }
        }
        Staff staff = modelMapper.map(s,Staff.class);
          staff= staffRepository.saveAndFlush(staff);
        return modelMapper.map(staff,StaffDto.class);
    }

    @Override
    public StaffDto findById(Integer id) {
        Optional<Staff> staff = staffRepository.findById(id);
        return staff.isEmpty() ? null : modelMapper.map(staff.get(),StaffDto.class);
    }

//    @Override
//    public StaffDto getById(Integer id) {
//        System.out.println(staffRepository.getById(id));
////        StaffDto staff = modelMapper.map(staffRepository.getById(id),StaffDto.class);
//        return staffRepository.getById(id) == null ? null : modelMapper.map(staffRepository.getById(id),StaffDto.class);
//    }
    @Override
    public StaffDto login(Integer id, String password){
        Optional<Staff> staff= staffRepository.findById(id);
       if(staff.isPresent() && bCryptPasswordEncoder.matches(password,staff.get().getPassword())){
           staff.get().setPassword("");
           return modelMapper.map(staff.get(),StaffDto.class);
       }
       return null;
    }
}
