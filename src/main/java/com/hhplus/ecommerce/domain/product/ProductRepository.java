package com.hhplus.ecommerce.domain.product;

import java.util.List;

public interface ProductRepository {
    List<Product> findbyBrandId(Long brandId);
    List<ProductStock> findbyProductId(Long productId);
    List<Product> saveAllProduct(List<Product> products);
    List<ProductStock> saveAllProductStock(List<ProductStock> stocks);
}
