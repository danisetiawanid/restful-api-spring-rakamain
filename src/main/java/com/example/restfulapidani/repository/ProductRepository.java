package com.example.restfulapidani.repository;

import com.example.restfulapidani.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
