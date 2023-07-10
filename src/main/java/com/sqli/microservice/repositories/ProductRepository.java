package com.sqli.microservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sqli.microservice.entities.Product;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value="select * from product where category_id = 1",nativeQuery = true)
    List<Product> findByCategoryId();
    
    @Query(value="select product.* from product,product_category where product.category_id =product_category.id and product_category.category_name = 'BOOKS'",nativeQuery = true)
    List<Product> findByNameContaining();


}
