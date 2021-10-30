package com.example.dienthoaiviet.controller.admin;

import com.example.dienthoaiviet.dto.*;
import com.example.dienthoaiviet.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("products")
public class ProductController {
    @Autowired
    private IProductservice productservice;
    @Autowired
    private IProduct_detailsService product_detailsService;
    @Autowired
    private IPropertiesService propertiesService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IImageService iImageService;
    @GetMapping
    public String findAllProduct(Model model){
        model.addAttribute("listProducts",productservice.findAll());
        return "admin/product/products";

    }
    @GetMapping("/add")
    public String addProduct(){
        return "admin/product/addProduct";
    }
    @GetMapping("/{id}")
    public String findById(@PathVariable String id, Model model){
        ProductsDto productsDto = productservice.findAllById(id);
        if(productsDto==null){
            return "redirect:/products";
        }
        model.addAttribute("product", productsDto);
        model.addAttribute("listImageProduct",iImageService.findAllByIdProduct(id));
        model.addAttribute("listProductDetails",product_detailsService.findAllByProductId(id));
        return "admin/product/editProduct";
    }
    @ModelAttribute("listCategory")
    public List<CategoryDto> findAllCategory(){
        return categoryService.findAll();
    }
    @ModelAttribute("listProperties")
    public List<PropertiesDto> findAllProperties(){
        return propertiesService.findAll();
    }
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductsDto productsDto, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.ok("false");
        }
        if(productservice.findAllById(productsDto.getId())!=null){
            return ResponseEntity.ok("same id");
        }
        productsDto.setStatus(true);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(productsDto.getCategory_id());
        productsDto.setCategory(categoryDto);
        PropertiesDto propertiesDto = new PropertiesDto();
        propertiesDto.setId(productsDto.getProperties_id());
        productsDto.setProperties(propertiesDto);
        if(productservice.saveAndFlush(productsDto)==null){
            return ResponseEntity.ok("false");
        }
        for (String x : productsDto.get_lisImageAdd()){
            ImageDto imageDto = new ImageDto();
            imageDto.setProducts(productsDto);
            imageDto.setName(x);
            iImageService.saveAndFlush(imageDto);
        }
        int i=0;
        for (String x : productsDto.getListColor()){
            Products_detailsDto products_detailsDto = new Products_detailsDto();
            products_detailsDto.setId(productsDto.getId()+i);
            products_detailsDto.setQuantity(0);
            products_detailsDto.setProducts(productsDto);
            products_detailsDto.setColor(x);
            product_detailsService.saveAndFlush(products_detailsDto);
            i++;
        }
        return ResponseEntity.ok("true");
    }
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateProduct(@Valid @RequestBody ProductsDto productsDto , @PathVariable String id, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.ok("false");
        }
        productsDto.setStatus(true);
        productsDto.setId(id);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(productsDto.getCategory_id());
        productsDto.setCategory(categoryDto);
        PropertiesDto propertiesDto = new PropertiesDto();
        propertiesDto.setId(productsDto.getProperties_id());
        productsDto.setProperties(propertiesDto);
       ProductsDto productsDto1 =productservice.saveAndFlush(productsDto);
        if(productsDto1==null){
            return ResponseEntity.ok("false");
        }
//        for (String x : productsDto.get_lisImageAdd()){
//            ImageDto imageDto = new ImageDto();
//            imageDto.setProducts(productsDto);
//            imageDto.setName(x);
//            iImageService.saveAndFlush(imageDto);
//        }
        return ResponseEntity.ok(productsDto1);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        ProductsDto productsDto = productservice.findAllById(id);
        if(productsDto==null){
            return ResponseEntity.ok("no product");
        }
        productsDto.setStatus(false);
        return ResponseEntity.ok(productservice.saveAndFlush(productsDto));
    }
}
