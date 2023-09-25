package com.macv.market.web.controller;

import com.macv.market.domain.Product;
import com.macv.market.domain.service.ProductService;
import com.macv.market.web.utils.ResponseWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ApiOperation("Returns all products")
    //En este caso como sólo está parametrizada una respuesta 200 sólo se documentará esa
    @ApiResponse(code = 200, message = "X products found")
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

    @GetMapping("/getScarse/{stock}")
    public ResponseEntity<ResponseWrapper<Product>> getScarseProducts(@PathVariable("stock") int stockQuantity){
        List<Product> data;
        String message;
        HttpStatus status = HttpStatus.OK;

        List<Product> productsResult = productService.getScarseProducts(stockQuantity);

        if(productsResult.isEmpty()){
            data = null;
            message = "0 products have less than " + stockQuantity + " units in stock";
        } else {
            data = productsResult;
            message = productsResult.size() + " products have less than " + stockQuantity + " units in stock";
        }

        ResponseWrapper<Product> responseWrapper = new ResponseWrapper<>(
                message,
                data
        );
        return new ResponseEntity<>(responseWrapper, status);
    }

    @GetMapping("id/{id}")
    @ApiOperation("Returns a product seached by its id")
    //Como en este caso se tienen 2 respuestas se pueden documentar ambas
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product found"),
            @ApiResponse(code = 404, message = "Product not found")
    })

    //Adicionalmente se puede agregar información de los parámetros
    public ResponseEntity
            <ResponseWrapper<Optional<Product>>> getById(
                    @ApiParam(
                            value = "Product id to be searched",
                            required = true,
                            example = "12"
                    )
                    @PathVariable("id") int productId
    ){

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
