package com.macv.market.domain.repository;

import com.macv.market.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAll();
    List<Product> getByCategory(int categoryId);
    List<Product> getScarseProducts(int stockQuantity);
    Optional<Product> getById(int productId);
    Product save(Product product);
    void delete(int productId);
}
