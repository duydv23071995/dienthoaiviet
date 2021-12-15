package com.example.dienthoaiviet.jpaRepository;

import com.example.dienthoaiviet.entity.Category;
import com.example.dienthoaiviet.entity.Products;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products,String> {
    @Override
    <S extends Products> S saveAndFlush(S s);

    @Query("select o from Products o where o.status=true")
    List<Products> findAll();

    @Query("select o from Products o where o.status=true and o.id=?1")
    Optional<Products> findAllById(String id);

    @Query("select  o from Products  o where o.status=true and o.discout>0 order by o.discout desc ")
    List<Products> findHotsale(Pageable pageable);

    @Query("select o from  Products o where o.status=true and o.category.id=?1")
    List<Products> findAllByIdCategory(Integer id);

    @Query("select o from  Products o where o.status=true and o.properties.id=?1")
    List<Products> findAllByIdProperties(Integer id);

    @Query("select o from Products o where o.status=true  and o.category.id not in (1,2)")
    List<Products> findByIdCategoryOther();
    @Query("select o from Products o where o.status=true and o.price >= ?1 and o.price < ?2")
    List<Products> findByPrice(int priceLow,int expensive);
    @Query("select o from Products o where o.category.id in ?1 and o.properties.id in ?2 and  o.price between ?3 and ?4 and o.status=true")
    List<Products> findBySort(String category,String properties,int old,int expesite);
    @Query("select o from Products o where o.category.id in ?1 and o.status=true")
    List<Products> findAllByTest(List<Category> categoryList);

    @Query("select o from  Products o where o.status=true and o.name like ?1")
    List<Products> findAllByKeyword(String keyWord);
}
