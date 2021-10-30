package com.example.dienthoaiviet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangepassworDto {
    private String pass;
    private String pass1;
    private String pass2;
}
