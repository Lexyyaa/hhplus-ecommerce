package com.hhplus.ecommerce.infra.repository.product;


import com.hhplus.ecommerce.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJPARepository extends JpaRepository<Product, Long> {
    List<Product> findByBrandId(Long brandId);
}
