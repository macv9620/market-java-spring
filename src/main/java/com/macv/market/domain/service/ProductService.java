package com.macv.market.domain.service;

import com.macv.market.domain.Product;
import com.macv.market.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    //Se inyecta el ProductRepository
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public List<Product> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    }

    public List<Product> getScarseProducts(int stockQuantity){
        return productRepository.getScarseProducts(stockQuantity);
    }

    public Optional<Product> getById(int productId){
        return productRepository.getById(productId);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public boolean delete(int productId){
        if(getById(productId).isPresent()){
            productRepository.delete(productId);
            return true;
        } else {
            return false;
        }
    }
}
