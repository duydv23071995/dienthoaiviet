package com.example.dienthoaiviet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


@Data
@NoArgsConstructor @AllArgsConstructor
public class ProductsDto {
    @NotEmpty
    private String id;
    private String details;
    @NotEmpty
    private String name;
    @NotNull
    @DecimalMin("1000")
    private int price;
    @NotNull
    @DecimalMin("0")
    private  int discout;
    @NotNull
    @DecimalMin("1000")
    private int importprice;
    @NotNull
    private String image;
    private  boolean status;
    private CategoryDto category;
    private PropertiesDto properties;
    @NotNull
    private Integer properties_id;
    @NotNull
    private Integer category_id;
   private List<String> _lisImageAdd;
   private List<String> listColor;
   public double hostSale(){
       double sale = discout/100.0;
       return (1-sale)*price;
   }
   private String convert_Price;
   private String convert_PriceDiscout;

    public String getConvert_PriceDiscout() {
        DecimalFormat format = new DecimalFormat("###,###,###");
        double money = hostSale();
        return format.format(money)+"đ";
    }

    public String getConvert_Price() {
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(price)+"đ";
    }

}
