package com.example.dienthoaiviet.service.impl;

import com.example.dienthoaiviet.dto.Products_detailsDto;
import com.example.dienthoaiviet.service.IProduct_detailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CookieService {
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    @Autowired
    private IProduct_detailsService product_detailsService;
    public void create(String name, String value, int days){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(days*60*60);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    public String get(String name){
        Cookie [] cookies = request.getCookies();
        for (Cookie x : cookies){
            if(x.getName().equalsIgnoreCase(name)){
                return x.getValue();
            }
        }
        return  null;
    }
    public List<Products_detailsDto> findListProductdetails(){
        List<Products_detailsDto> products_detailsDtos = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        for (Cookie x : cookies){
            if(x.getName().contains("IdProduct")){
                String id =x.getName().replace("IdProduct","");
                Products_detailsDto products_detailsDto = product_detailsService.findAllById(id);
                products_detailsDto.setQuantity(Integer.parseInt(x.getValue()));
                products_detailsDtos.add(products_detailsDto);
            }
        }
        return products_detailsDtos;
    }
}
