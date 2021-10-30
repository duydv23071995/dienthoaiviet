package com.example.dienthoaiviet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Top10Product implements Serializable {
    @Id
    private String id;
    private String name;
    private int price;
    private  int discout;
    private int importprice;
    private String image;
    private long quantity;
}
