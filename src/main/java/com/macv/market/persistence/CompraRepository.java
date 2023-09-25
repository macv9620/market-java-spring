package com.macv.market.persistence;

import com.macv.market.domain.Purchase;
import com.macv.market.domain.repository.PurchaseRepository;
import com.macv.market.persistence.crud.CompraCrudRepository;
import com.macv.market.persistence.entity.Compra;
import com.macv.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> getAllPurchases() {
        return purchaseMapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getPurchasesByClientId(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId).map(compras -> purchaseMapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = purchaseMapper.toCompra(purchase);

        //Debido a que la relación de productos no se está mapeando se debe establecer la relación entre producto y compra
        //Compras ya conoce sus productos y con lo siguiente le estamos diciendo al producto a qué compra pertenece
        //Ahora para que el guardado se haga en cascada se debe modificar la relación en Compra
        compra.getProductos().forEach(producto -> producto.setCompra(compra));

        return purchaseMapper.toPurchase(compraCrudRepository.save(compra));
    }
}
