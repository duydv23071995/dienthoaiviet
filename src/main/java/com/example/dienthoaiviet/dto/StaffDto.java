package com.example.dienthoaiviet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffDto  {
    private Integer id;
    private String password;
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String address;
    @NotEmpty
    @Email
    private String email;
    @Pattern(regexp = "[\\+]?[(]?[0-9]{3}[)]?[-\\.]?[0-9]{3}[-\\.]?[0-9]{4,6}")
    private String phoneNumber;
//    private MultipartFile img;
    @NotEmpty
    private String image;
    @NotNull
    @DecimalMin("1000")
    private Long salary;
    @NotNull
    private boolean gender;
    private boolean status;
    private Date birthDay;
    private RoleDto role;
    private Integer role_id;
    public String convertDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(birthDay);
    }
}
