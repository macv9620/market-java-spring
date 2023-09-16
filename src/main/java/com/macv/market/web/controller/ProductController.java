package com.macv.market.web.controller;

import com.macv.market.domain.Product;
import com.macv.market.domain.service.ProductService;
import com.macv.market.web.controller.utils.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    //Se inyecta el ProductService
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ResponseWrapper<Product>> getAll(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("DataBaseType", "PostgreSQL");

        List<Product> productList = productService.getAll();

        ResponseWrapper<Product> responseWrapper = new ResponseWrapper<>(
                productList.size() + " products found",
                productList
        );
        return new ResponseEntity<>(responseWrapper, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ResponseWrapper<Product>> getByCategory(@PathVariable("categoryId") int categoryId){

        List<Product> productList = productService.getByCategory(categoryId);

        ResponseWrapper<Product> responseWrapper = new ResponseWrapper<>(
                productList.size() + " products found for category " + categoryId,
                productList
        );
        return new ResponseEntity<>(responseWrapper, HttpStatus.OK);
    }

    public List<Product> getScarseProducts(int stockQuantity){
        return productService.getScarseProducts(stockQuantity);
    }

    @GetMapping("id/{id}")
    public ResponseEntity
            <ResponseWrapper<Optional<Product>>> getById(@PathVariable("id") int productId){

        Optional<Product> productFound = productService.getById(productId);
        ResponseWrapper<Optional<Product>> responseWrapper = null;

        if (productFound.isEmpty()){
            responseWrapper = new ResponseWrapper<>(
                    "Product " + productId + " not found",
                    Optional.of(productService.getById(productId))
            );
            return new ResponseEntity<>(responseWrapper, HttpStatus.NOT_FOUND) ;
        } else {
            responseWrapper = new ResponseWrapper<>(
                    "Detail for product " + productId,
                    Optional.of(productFound)
            );
            return new ResponseEntity<>(responseWrapper, HttpStatus.OK) ;
        }
    }

    @PostMapping("/new")
    public ResponseEntity<ResponseWrapper<Product>> save(@RequestBody Product product){

        if(product.checkFields()){
            ResponseWrapper<Product> responseWrapper = new ResponseWrapper<>(
                    "Invalid fields values"
            );
            return new ResponseEntity<>(responseWrapper, HttpStatus.BAD_REQUEST);
        }

        ResponseWrapper<Product> responseWrapper = new ResponseWrapper<>(
                "Product created successfully",
                productService.save(product)
        );
        return new ResponseEntity<>(responseWrapper, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseWrapper<Boolean>> delete(@PathVariable("id") int productId) {
        String message;
        boolean data;
        HttpStatus status;

        if (!productService.delete(productId)){
            message = "Product " + productId + " not found";
            data = false;
            status = HttpStatus.NOT_FOUND;
        } else {
            message = "Product " + productId + " deleted successfully";
            data = true;
            status = HttpStatus.OK;
        }

        ResponseWrapper<Boolean> responseWrapper = new ResponseWrapper<>(
                message,
                data
        );
        return new ResponseEntity<>(responseWrapper, status);
    }
}
