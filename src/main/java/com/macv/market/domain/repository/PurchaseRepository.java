package com.macv.market.domain.repository;

import com.macv.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    //Obtener toda la lista de compras
    List<Purchase> getAllPurchases();

    //Obtener las compras que un usuario ha hecho;
    Optional<List<Purchase>> getPurchasesById(String clientId);

    //Guardar compra
    Purchase save(Purchase purchase);
}
