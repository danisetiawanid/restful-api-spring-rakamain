package com.example.restfulapidani.controller;

import com.example.restfulapidani.model.Product;
import com.example.restfulapidani.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
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


    @PostMapping(value = "/product", consumes = "application/json", produces = "application/json")
    public Product createProduct(@RequestBody Product newProduct){

        return this.productRepository.save(newProduct);
    }

    @DeleteMapping(value = "/product/{id}", produces = "application/json")

    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        if (this.productRepository.findById(id).isEmpty()) {
            return ResponseEntity.status(400).body("Product Not Found");
        }

        this.productRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }

    @PutMapping(value = "/product/{id}", produces = "application/json")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product updateProduct){
        if (this.productRepository.findById(id).isEmpty()) {
            return ResponseEntity.status(400).body("Product not Found");
        }
        updateProduct.setId(id);
        this.productRepository.save(updateProduct);
        return ResponseEntity.ok("Success");
    }



    @PatchMapping(value = "/product", produces = "application/json")

    public String patchProduct(){
        return "Patch Product";
    }
}