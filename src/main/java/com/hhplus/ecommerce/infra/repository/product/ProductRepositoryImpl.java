package com.hhplus.ecommerce.infra.repository.product;


import com.hhplus.ecommerce.domain.product.Product;
import com.hhplus.ecommerce.domain.product.ProductRepository;
import com.hhplus.ecommerce.domain.product.ProductStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJPARepository productJPARepository;
    private final ProductStockJPARepositpory productStockJPARepositpory;

    @Override
    public List<Product> findbyBrandId(Long brandId) {
        return productJPARepository.findByBrandId(brandId);
    }

    @Override
    public List<ProductStock> findbyProductId(Long productId) {
        return productStockJPARepositpory.findByProductId(productId);
    }

    @Override
    public List<Product> saveAllProduct(List<Product> products) {
        return productJPARepository.saveAll(products);
    }

    public List<ProductStock> saveAllProductStock(List<ProductStock> stocks) {
        return productStockJPARepositpory.saveAll(stocks);
    }
}
