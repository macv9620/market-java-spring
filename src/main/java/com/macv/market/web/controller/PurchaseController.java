package com.macv.market.web.controller;

import com.macv.market.domain.Purchase;
import com.macv.market.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
@CrossOrigin
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/new")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase){
        return new ResponseEntity<>(purchaseService.savePurchase(purchase), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Purchase>> getAll(){
            return new ResponseEntity<>(purchaseService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/clientId/{id}")
    public ResponseEntity<Optional<List<Purchase>>> getByClientId(@PathVariable("id")String clientId) {
        return new ResponseEntity<>(purchaseService.getAllByClientId(clientId), HttpStatus.OK);
    }

}
