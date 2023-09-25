package com.macv.market.domain.service;

import com.macv.market.domain.Purchase;
import com.macv.market.domain.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;

    public List<Purchase> getAll() {
        return purchaseRepository.getAllPurchases();
    }

    public Optional<List<Purchase>> getAllByClientId(String clientId) {
        return purchaseRepository.getPurchasesByClientId(clientId);
    }

    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }
}
