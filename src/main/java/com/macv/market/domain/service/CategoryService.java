package com.macv.market.domain.service;

import com.macv.market.domain.Category;
import com.macv.market.persistence.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoriaRepository categoriaRepository;
    public List<Category> getCategories(){
        return categoriaRepository.getAllCategories();
    }

    public Optional<Category> getCategoryById(int categoryId) {
        return categoriaRepository.getCategoryById(categoryId);
    }
}
