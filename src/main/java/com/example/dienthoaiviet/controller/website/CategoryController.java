package com.example.dienthoaiviet.controller.website;

import com.example.dienthoaiviet.dto.*;
import com.example.dienthoaiviet.service.ICategoryService;
import com.example.dienthoaiviet.service.IProductservice;
import com.example.dienthoaiviet.service.IPropertiesService;
import com.example.dienthoaiviet.service.impl.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/dienthoaiviet")
public class CategoryController {
    @Autowired
    private CookieService cookieService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IPropertiesService propertiesService;
    @Autowired
    private IProductservice productservice;
    @ModelAttribute("listProductCart")
    public List<Products_detailsDto> findAllProductCart(){
        return cookieService.findListProductdetails();
    }
    @ModelAttribute("sumMoneyProductCart")
    public int sumProductCart(){
        int sum =0;
        for (Products_detailsDto x:cookieService.findListProductdetails()){
            sum+=x.getQuantity()*(x.getProducts().getPrice()*(1-x.getProducts().getDiscout()/100.0));
        }
        return sum;
    }
    @ModelAttribute("categorywebstie")
    public List<CategoryDto> findAllCategory() {
        return categoryService.findAll();
    }
    @ModelAttribute("properties")
    public List<PropertiesDto> findAllProperties() {
        return propertiesService.findAll();
    }

    @GetMapping("/category/{id}")
    public String findCategory(@PathVariable int id, Model model) {
        System.out.println(id);
        if(id==0){
            model.addAttribute("listProductCategory", productservice.findAll());
        }else {
            model.addAttribute("listProductCategory", productservice.findAllByIdCategory(id));
        }
        model.addAttribute("idcategory",id);
        return "website/category/index";
    }
    @PostMapping("/loc")
    @ResponseBody
    public ResponseEntity<?> filter(@RequestBody FilterDto filterDto){
        List<ProductsDto> list=  new ArrayList<>();
        if(filterDto.getIdCategory()==0){
             list = productservice.findAll();
        }else {
            list = productservice.findAllByIdCategory(filterDto.getIdCategory());
        }
        List<ProductsDto> listProperties = new ArrayList<>();
        for (ProductsDto x : list){
           if(filterDto.getIdProperties()==0){
               listProperties.add(x);
           }else {
               if(x.getProperties().getId()==filterDto.getIdProperties()){
                   listProperties.add(x);
               }
           }
        }
        List<ProductsDto> listPrice = new ArrayList<>();
        for (ProductsDto x : listProperties){
            if(filterDto.getKhoanggia()==0){
                listPrice.add(x);
            }else if(filterDto.getKhoanggia()==1){
                if(x.getPrice()<5000000){
                    listPrice.add(x);
                }
            }else if(filterDto.getKhoanggia()==2){
                if(x.getPrice()>=5000000&& x.getPrice()<8000000){
                    listPrice.add(x);
                }
            }else if(filterDto.getKhoanggia()==3){
                if(x.getPrice()>=8000000&& x.getPrice()<13000000){
                    listPrice.add(x);
                }
            }else if(filterDto.getKhoanggia()==4){
                if(x.getPrice()>=13000000&& x.getPrice()<18000000){
                    listPrice.add(x);
                }
            }else if(filterDto.getKhoanggia()==5){
                if(x.getPrice()>=18000000){
                    listPrice.add(x);
                }
            }
        }
        if(filterDto.getSort()==1){
            Collections.sort(listPrice,(o1, o2) -> {
                return o1.getPrice() < o2.getPrice() ? 1:-1;
            });
        }
        if(filterDto.getSort()==2){
            Collections.sort(listPrice,(o1, o2) -> {
                return o1.getPrice() > o2.getPrice() ? 1:-1;
            });
        }
        return ResponseEntity.ok(listPrice);
    }
//    @GetMapping("/properties/{id}")
//    public String findProperties(@PathVariable int id, Model model) {
//        model.addAttribute("listProductCategory", productservice.findAllByIdProperties(id));
//        return "website/category/index";
//    }
//    @GetMapping("/price")
//    public String findPice(@RequestParam(name = "thap") int thap,@RequestParam(name = "cao") int cao, Model model) {
//
//        model.addAttribute("listProductCategory", productservice.findByPrice(thap,cao));
//        return "website/category/index";
//    }

}
