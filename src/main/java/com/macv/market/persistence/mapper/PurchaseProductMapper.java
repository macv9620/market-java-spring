package com.macv.market.persistence.mapper;
import com.macv.market.domain.PurchaseProduct;
import com.macv.market.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseProductMapper {

    @Mappings({
            @Mapping(source = "id.idProducto", target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(source = "producto", target = "product"),
    })
    PurchaseProduct toPurchaseProduct(ComprasProducto comprasProducto);
    List<PurchaseProduct> toPurchaseProducts(List<ComprasProducto> comprasProducto);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true)
    })
    ComprasProducto toComprasProducto(PurchaseProduct purchaseProduct);
}
