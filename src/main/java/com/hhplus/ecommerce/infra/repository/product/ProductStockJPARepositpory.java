package com.hhplus.ecommerce.infra.repository.product;

import com.hhplus.ecommerce.domain.product.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductStockJPARepositpory extends JpaRepository<ProductStock, Long> {
    List<ProductStock> findByProductId(Long productId);
}
