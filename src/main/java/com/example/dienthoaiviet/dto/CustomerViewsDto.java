package com.example.dienthoaiviet.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data
@NoArgsConstructor @AllArgsConstructor
public class CustomerViewsDto {
    private Integer id;
    private String name;
    @NotEmpty
    private String note;
    private Date date = new Date();
    @Pattern(regexp = "[\\+]?[(]?[0-9]{3}[)]?[-\\.]?[0-9]{3}[-\\.]?[0-9]{4,6}")
    private String phoneNumber;
    private ProductsDto products;
    private String view_date;

    public String getView_date() {
        return convertDate();
    }

    public String convertDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }

}
