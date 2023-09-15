package com.macv.market.web.controller;

import com.macv.market.domain.Product;
import com.macv.market.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    //Se inyecta el ProductService
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getByCategory(@PathVariable("categoryId") int categoryId){
        return productService.getByCategory(categoryId);
    }

    public List<Product> getScarseProducts(int stockQuantity){
        return productService.getScarseProducts(stockQuantity);
    }

    @GetMapping("id/{id}")
    public Optional<Product> getById(@PathVariable("id") int productId){
        return productService.getById(productId);
    }

    @PostMapping("/new")
    public Product save(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int productId) {
        return productService.delete(productId);
    }
}
