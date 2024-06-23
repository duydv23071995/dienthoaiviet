package com.example.dienthoaiviet.jpaRepository;

import com.example.dienthoaiviet.entity.Top10Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Top10ProductRepository extends JpaRepository<Top10Product,String> {
    @Query("SELECT new Top10Product(o.id,o.name,o.price,o.discout,o.importprice,o.image,SUM(c.quantity)) from Products o JOIN Products_details a ON a.products.id = o.id\n" +
            "JOIN Bill_details c ON c.products_details.id = a.id \n" +
            "JOIN Bill d on d.id = c.bill.id\n" +
            "WHERE o.status=true AND d.status=6  GROUP BY o.id,o.name,o.price,o.discout,o.importprice,o.image,SUM(c.quantity) order by SUM(c.quantity) desc ")
         List<Top10Product> findAllBy(Pageable pageable);
}
