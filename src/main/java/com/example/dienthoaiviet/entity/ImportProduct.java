package com.example.dienthoaiviet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "importproduct")
public class ImportProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date day;
    @ManyToOne @JoinColumn(name = "staffid")
    private Staff staff;
    @OneToMany(mappedBy = "importProduct")
    private List<ImportProductDetails> importProductDetailsList;
}
