package com.hhplus.ecommerce.interfaces.dto.product;

import lombok.Builder;

import java.util.List;

@Builder
public record ProductResponse(
        List<Product> products,
        List<ProductStock> productStocks,
        List<PopularProduct> popularProducts
) {
    public record Product(
            Long productId,
            Long brandId,
            String name
    ) {}

    public record ProductStock(
            Long stockId,
            Long productId,
            String sku,
            String name,
            Long quantity,
            Long price
    ) {}

    public record PopularProduct(
            Long productId,
            String name,
            int rank,
            Long totalSelling
    ) {}
}