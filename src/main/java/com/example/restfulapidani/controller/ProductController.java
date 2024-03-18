package com.example.restfulapidani.controller;

import com.example.restfulapidani.model.Product;
import com.example.restfulapidani.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController( ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping(value = "product", produces = "application/json")
    public Iterable<Product> getProduct(){
        return this.productRepository.findAll();
    }


    @PostMapping(value = "/product", produces = "application/json")
    public Product createProduct(@RequestBody Product newProduct){

        return this.productRepository.save(newProduct);
    }

    @PutMapping(value = "/product", produces = "application/json")
    public String updateProduct(){
        return "Update Product";
    }

    @PatchMapping(value = "/product", produces = "application/json")

    public String patchProduct(){
        return "Patch Product";
    }
}