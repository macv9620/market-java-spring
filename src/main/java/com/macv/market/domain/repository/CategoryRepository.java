package com.macv.market.domain.repository;

import com.macv.market.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> getAllCategories();
    Optional<Category> getCategoryById(int categoryId);
}
