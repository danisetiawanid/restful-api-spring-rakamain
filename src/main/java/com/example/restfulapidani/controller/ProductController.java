package com.example.restfulapidani.controller;

import com.example.restfulapidani.model.Product;
import com.example.restfulapidani.model.Response;
import com.example.restfulapidani.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "product", produces = "application/json")
    public Iterable<Product> getProduct() {
        return this.productRepository.findAll();
    }


    @PostMapping(value = "/product", consumes = "application/json", produces = "application/json")
    public Product createProduct(@RequestBody Product newProduct) {
        return this.productRepository.save(newProduct);
    }

    @DeleteMapping(value = "/product/{id}", produces = "application/json")

//    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
//        if (this.productRepository.findById(id).isEmpty()) {
//            return ResponseEntity.status(400).body("Product Not Found");
//        }
//
//        this.productRepository.deleteById(id);
//        return ResponseEntity.ok("Deleted");
//    }

    public ResponseEntity<Response> deleteProduct(@PathVariable Long id) {
        if (this.productRepository.findById(id).isEmpty()) {
            return ResponseEntity.badRequest().body(new Response("Product not found"));
        }


        this.productRepository.deleteById(id);
        Response response = new Response("Delete Success", null);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/product/{id}", produces = "application/json")
    public ResponseEntity<Response> updateProduct(@PathVariable Long id, @RequestBody Product updateProduct) {

        if (this.productRepository.findById(id).isEmpty()) {
//            return ResponseEntity.status(400).body("Product not Found");
            return ResponseEntity.badRequest().body(new Response("Product Not Found"));
        }
        updateProduct.setId(id);
        Response response = new Response("Success", this.productRepository.save(updateProduct));
        return ResponseEntity.ok(response);
    }


    @PatchMapping(value = "/product/{id}", produces = "application/json")

    public ResponseEntity<String> patchProduct(@PathVariable Long id, @RequestBody Product updateProduct) {
        Optional<Product> productOptional = this.productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Product Not Found");
        }
        Product product = productOptional.get();
        if (updateProduct.getName() != null) {
            product.setName(updateProduct.getName());
        }
        if (updateProduct.getQty() != null) {
            product.setQty(updateProduct.getQty());
        }
        if (updateProduct.getPrice() != null) {
            product.setPrice(updateProduct.getPrice());
        }
        this.productRepository.save(product);
        return ResponseEntity.ok("Patch Success");

    }
}