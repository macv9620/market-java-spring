package com.macv.market.web.controller;

import com.macv.market.domain.Category;
import com.macv.market.domain.service.CategoryService;
import com.macv.market.web.utils.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseWrapper<Category>> getCategories(){
        List<Category> categories = categoryService.getCategories();
        String message;
        List<Category> data;

        if (categories.isEmpty()){
            message = "0 categories found";
            data = null;
        } else {
            message = categories.size() +" categories found";
            data = categories;
        }
        ResponseWrapper<Category> responseWrapper = new ResponseWrapper<Category>(message, data);
        return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseWrapper<Optional<Category>>> getCategoryById(@PathVariable("id") int categoryId){
        Optional<Category> category = categoryService.getCategoryById(categoryId);
        String message;
        HttpStatus status;

        if (category.isEmpty()){
            message = "Category " + categoryId + " not found";
            status = HttpStatus.NOT_FOUND;
        } else {
            message = "Details for category " + categoryId;
            status = HttpStatus.OK;
        }
        ResponseWrapper<Optional<Category>> responseWrapper = new ResponseWrapper<Optional<Category>>(
                message,
                category
        );
        return new ResponseEntity<>(responseWrapper, status);
    }
}
