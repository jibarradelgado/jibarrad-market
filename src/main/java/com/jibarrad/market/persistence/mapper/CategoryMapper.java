package com.jibarrad.market.persistence.mapper;

import com.jibarrad.market.domain.CategoryDomain;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "idCategory", target = "categoryId"),
            @Mapping(source = "description", target = "category"),
            @Mapping(source = "state", target = "active"),
    })
    CategoryDomain toCategory(com.jibarrad.market.persistence.entity.Category category);

    @InheritInverseConfiguration
    @Mapping(target = "products", ignore = true)
    com.jibarrad.market.persistence.entity.Category toCategory(CategoryDomain categoryDomain);
}
