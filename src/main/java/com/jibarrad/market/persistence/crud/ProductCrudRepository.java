package com.jibarrad.market.persistence.crud;

import com.jibarrad.market.persistence.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCrudRepository extends CrudRepository<ProductEntity, Integer> {
    //@Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    public List<ProductEntity> findByIdCategoryOrderByNameAsc(int idCategory);

    Optional<List<ProductEntity>> findByStockQuantityLessThanAndState(int stockQuantity, boolean state);
}
