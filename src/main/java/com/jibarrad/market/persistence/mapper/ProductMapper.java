package com.jibarrad.market.persistence.mapper;

import com.jibarrad.market.domain.ProductDomain;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "idProduct", target = "productId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "idCategory", target = "categoryId"),
            @Mapping(source = "salePrice", target = "price"),
            @Mapping(source = "stockQuantity", target = "stock"),
            @Mapping(source = "state", target = "active"),
            @Mapping(source = "category", target = "category"),
    })
    ProductDomain toProduct(com.jibarrad.market.persistence.entity.Product product);
    List<ProductDomain> toProducts(List<com.jibarrad.market.persistence.entity.Product> products);

    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras", ignore = true)
    com.jibarrad.market.persistence.entity.Product toProduct(ProductDomain productDomain);
}
