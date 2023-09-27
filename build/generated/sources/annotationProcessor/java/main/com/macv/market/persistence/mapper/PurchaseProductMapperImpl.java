package com.macv.market.persistence.mapper;

import com.macv.market.domain.PurchaseProduct;
import com.macv.market.persistence.entity.ComprasProducto;
import com.macv.market.persistence.entity.ComprasProductoPK;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-27T06:32:54-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 11.0.20 (Eclipse Adoptium)"
)
@Component
public class PurchaseProductMapperImpl implements PurchaseProductMapper {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PurchaseProduct toPurchaseProduct(ComprasProducto comprasProducto) {
        if ( comprasProducto == null ) {
            return null;
        }

        PurchaseProduct purchaseProduct = new PurchaseProduct();

        Integer idProducto = comprasProductoIdIdProducto( comprasProducto );
        if ( idProducto != null ) {
            purchaseProduct.setProductId( idProducto );
        }
        purchaseProduct.setQuantity( comprasProducto.getCantidad() );
        purchaseProduct.setActive( comprasProducto.getEstado() );
        purchaseProduct.setProduct( productMapper.toProduct( comprasProducto.getProducto() ) );
        purchaseProduct.setTotal( comprasProducto.getTotal() );

        return purchaseProduct;
    }

    @Override
    public List<PurchaseProduct> toPurchaseProducts(List<ComprasProducto> comprasProducto) {
        if ( comprasProducto == null ) {
            return null;
        }

        List<PurchaseProduct> list = new ArrayList<PurchaseProduct>( comprasProducto.size() );
        for ( ComprasProducto comprasProducto1 : comprasProducto ) {
            list.add( toPurchaseProduct( comprasProducto1 ) );
        }

        return list;
    }

    @Override
    public ComprasProducto toComprasProducto(PurchaseProduct purchaseProduct) {
        if ( purchaseProduct == null ) {
            return null;
        }

        ComprasProducto comprasProducto = new ComprasProducto();

        comprasProducto.setId( purchaseProductToComprasProductoPK( purchaseProduct ) );
        comprasProducto.setCantidad( purchaseProduct.getQuantity() );
        comprasProducto.setEstado( purchaseProduct.getActive() );
        comprasProducto.setProducto( productMapper.toProducto( purchaseProduct.getProduct() ) );
        comprasProducto.setTotal( purchaseProduct.getTotal() );

        return comprasProducto;
    }

    private Integer comprasProductoIdIdProducto(ComprasProducto comprasProducto) {
        if ( comprasProducto == null ) {
            return null;
        }
        ComprasProductoPK id = comprasProducto.getId();
        if ( id == null ) {
            return null;
        }
        Integer idProducto = id.getIdProducto();
        if ( idProducto == null ) {
            return null;
        }
        return idProducto;
    }

    protected ComprasProductoPK purchaseProductToComprasProductoPK(PurchaseProduct purchaseProduct) {
        if ( purchaseProduct == null ) {
            return null;
        }

        ComprasProductoPK comprasProductoPK = new ComprasProductoPK();

        comprasProductoPK.setIdProducto( purchaseProduct.getProductId() );

        return comprasProductoPK;
    }
}
