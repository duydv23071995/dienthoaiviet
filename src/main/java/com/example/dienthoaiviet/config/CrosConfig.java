//package com.example.dienthoaiviet.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
///**
// * Tên DA: ManageStudent
// * Mô Tả:
// * Tên Class :
// * Version         Date            By            Change/Comment
// * 1.00          11/21/2021         GMO_DuyDV     create
// */
//@Configuration
//public class CrosConfig {
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("http://localhost:3000/");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//
//        source.registerCorsConfiguration("/api/**", config);
//        return new CorsFilter(source);
//    }
//
//}
