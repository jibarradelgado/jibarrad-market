package com.jibarrad.market.persistence.crud;

import com.jibarrad.market.persistence.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCrudRepository extends CrudRepository<Product, Integer> {
    //@Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    public List<Product> findByIdCategoryOrderByNameAsc(int idCategory);

    Optional<List<Product>> findByStockQuantityLessThanAndState(int stockQuantity, boolean state);
}
