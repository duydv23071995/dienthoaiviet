package com.example.dienthoaiviet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CustomerDto {
    private Integer id;
    @NotEmpty
    private  String fullName;
    @NotEmpty
    private String address;
    @Email
    private String email;
    @Pattern(regexp = "[\\+]?[(]?[0-9]{3}[)]?[-\\.]?[0-9]{3}[-\\.]?[0-9]{4,6}")
    private String phoneNumber;
    private boolean status;
}
