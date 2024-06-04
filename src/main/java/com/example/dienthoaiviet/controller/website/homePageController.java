package com.example.dienthoaiviet.controller.website;

import com.example.dienthoaiviet.dto.*;
import com.example.dienthoaiviet.service.*;
import com.example.dienthoaiviet.service.impl.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/dienthoaiviet")
public class homePageController {
    @Autowired
    private IProductservice productservice;
    @Autowired
    private IProduct_detailsService product_detailsService;
    @Autowired
    private IImageService iImageService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IPropertiesService propertiesService;
    @Autowired
    private ICustomerViewsService customerViewsService;
    @Autowired
    private CookieService cookieService;
    @Autowired
    private ITop10ProductService top10ProductService;
    @ModelAttribute("categorywebstie")
    public List<CategoryDto> findAllCategory(){
        return categoryService.findAll();
    }
    @ModelAttribute("properties")
    public List<PropertiesDto> findAllProperties(){
        return propertiesService.findAll();
    }
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
    @GetMapping
    public String home(Model model){
        model.addAttribute("productHost",productservice.findHotsale());
        model.addAttribute("productSamsung",productservice.findAllByIdCategory(2));
        model.addAttribute("productIphone",productservice.findAllByIdCategory(1));
        model.addAttribute("productOther",productservice.findByIdCategoryOther());
        model.addAttribute("top10product",top10ProductService.findAllBy());
        System.out.println(top10ProductService.findAllBy().size());
        model.addAttribute("checkMenu", true);
        return "website/home/homePage";
    }
    @GetMapping("/{id}")
    public String getProductSingle(Model model, @PathVariable String id){
        ProductsDto productsDto = productservice.findAllById(id);
        List<ProductsDto> productsDtoList = productservice.findByPrice(productsDto.getPrice()-2000000,productsDto.getPrice()+2000000);
        if(productsDto==null){
            return "redirect:/dienthoaiviet";
        }
        model.addAttribute("imgProduct",iImageService.findAllByIdProduct(id));
        model.addAttribute("product",productsDto);
        model.addAttribute("productFeatured",productsDtoList);
        model.addAttribute("productDetailsSinggle",product_detailsService.findAllByProductId(id));
        model.addAttribute("listComent",customerViewsService.findAll(id));
        return "website/product/index";
    }
    @GetMapping("/tim-kiem/{key}")
    public String findNameProduct(@PathVariable String key,Model model){
        model.addAttribute("listProductBYkey", productservice.findAllByKeyword(key));
        return "website/product/productSeach";
    }

    @PostMapping("/customer-views/{id}")
    public ResponseEntity<?> customerViews(@Valid @RequestBody CustomerViewsDto customerViewsDto, BindingResult result,@PathVariable String id){
        if(result.hasErrors()){
            return ResponseEntity.ok(false);
        }
        System.out.println(customerViewsDto);
        ProductsDto productsDto = new ProductsDto();
        productsDto.setId(id);
        customerViewsDto.setProducts(productsDto);
        customerViewsDto=customerViewsService.saveAndFlush(customerViewsDto);
        System.out.println(customerViewsDto);
        return ResponseEntity.ok(customerViewsDto);
    }
}
