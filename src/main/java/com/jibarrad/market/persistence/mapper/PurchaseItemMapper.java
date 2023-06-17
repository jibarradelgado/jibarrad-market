package com.jibarrad.market.persistence.mapper;

import com.jibarrad.market.domain.PurchaseItem;
import com.jibarrad.market.persistence.entity.PurchaseProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {
    @Mappings({
            @Mapping(source = "id.idProduct", target = "productId"),
            @Mapping(source = "quantity", target = "quantity"),
            //This mapping can be removed because it uses the same name
            @Mapping(source = "total", target = "total"),
            @Mapping(source = "state", target = "active")
    })
    PurchaseItem toPurchaseItem(PurchaseProductEntity purchaseProduct);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "purchaseEntity", ignore = true),
            @Mapping(target = "productEntity", ignore = true),
            @Mapping(target = "id.idPurchase", ignore = true)
    })
    PurchaseProductEntity toPurchaseItem(PurchaseItem item);


}
