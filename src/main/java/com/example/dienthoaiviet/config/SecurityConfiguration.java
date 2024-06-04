//package com.example.dienthoaiviet.config;
//
//import com.example.dienthoaiviet.dto.StaffDto;
//import com.example.dienthoaiviet.service.IStaffService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import javax.sql.DataSource;
//
///**
// * Description:
// *
// * @author: GMO_DuyDV
// * @version: 1.0
// * @since: 12/15/2021
// * Project_name: GMO_QuanLyTaiSan
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private IStaffService staffService;
//
//    @Bean
//    BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(staffService).passwordEncoder(passwordEncoder());
//
////        auth.jdbcAuthentication()
////                .dataSource(dataSource)
////                .usersByUsernameQuery("select email, password, status " +
////                        "from staff where email = ? and status = true")
////                .authoritiesByUsernameQuery("select email, role.name as'role' from staff join role on staff.roleid =role.id where email = ?")
////                .passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//
//        http.authorizeRequests().antMatchers("/users").hasAnyAuthority("Quản Lý")
//                .and().exceptionHandling().accessDeniedPage("/home");
//        ;
//
//        http.authorizeRequests().antMatchers("/dienthoaiviet", "/login").permitAll();
//
//        http.authorizeRequests().anyRequest().authenticated();
//
//        http.authorizeRequests().and().formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/home")
//                .failureUrl("/login?admin=true")
//                .usernameParameter("username")
//                .passwordParameter("password");
//
//        // Cấu hình cho Logout Page.
//        http.logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutUrl("/j_spring_security_logout")
//                .logoutSuccessUrl("/login");
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(
//                "/resources/**"
//                , "/static/**"
//                , "/admin/assets/**"
//                , "/images/**"
//                , "/login/css/**"
//                , "/login/img/**"
//                , "/login/js/**"
//                , "/login/scss/**"
//                , "/login/vendor/**"
//                , "/website/assets/**"
//        );
//    }
//}
