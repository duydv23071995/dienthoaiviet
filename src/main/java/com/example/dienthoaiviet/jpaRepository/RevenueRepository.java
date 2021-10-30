//package com.example.dienthoaiviet.jpaRepository;
//
//import com.example.dienthoaiviet.entity.Revenue;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface RevenueRepository extends JpaRepository<Revenue,String> {
//    @Query("select new Revenue (?1,SUM(o.sumMoney)) from Bill o " +
//            "WHERE o.status=6 GROUP by ?1")
//    List<Revenue> findAll(String day);
//}
