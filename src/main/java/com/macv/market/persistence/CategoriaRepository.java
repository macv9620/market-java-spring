package com.macv.market.persistence;

import com.macv.market.domain.Category;
import com.macv.market.domain.repository.CategoryRepository;
import com.macv.market.persistence.crud.CategoriaCrudRepository;
import com.macv.market.persistence.entity.Categoria;
import com.macv.market.persistence.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CategoriaRepository implements CategoryRepository {
    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    CategoriaCrudRepository categoriaCrudRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Categoria> categorias = ((List<Categoria>) categoriaCrudRepository.findAll());
        return categoryMapper.toCategories(categorias);
    }

    @Override
    public Optional<Category> getCategoryById(int categoryId) {
        return categoriaCrudRepository.findById(categoryId).map(cat -> categoryMapper.toCategory(cat));
    }
}
