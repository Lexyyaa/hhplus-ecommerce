package com.hhplus.ecommerce.interfaces.dto.product;

import com.hhplus.ecommerce.domain.product.ProductInfo;
import lombok.Builder;

import java.util.List;

@Builder
public record ProductResponse(
        List<Product> products,
        List<ProductStock> productStocks,
        List<PopularProduct> popularProducts
) {
    @Builder
    public record Product(
            Long productId,
            Long brandId,
            String name
    ) {
        public static List<ProductResponse.Product> from(List<ProductInfo.Item> products){
            return  products.stream()
                    .map(product -> Product.builder()
                    .productId(product.productId())
                    .brandId(product.brandId())
                    .name(product.name())
                    .build())
                    .toList();
        }
    }
    @Builder
    public record ProductStock(
            Long stockId,
            Long productId,
            String sku,
            String name,
            Long quantity,
            Long price
    ) {
        public static List<ProductResponse.ProductStock> from(List<ProductInfo.ItemStock> stocks){
            return  stocks.stream()
                    .map(stock -> ProductStock.builder()
                        .stockId(stock.stockId())
                        .productId(stock.productId())
                        .sku(stock.sku())
                        .name(stock.name())
                        .quantity(stock.quantity())
                        .price(stock.price())
                        .build())
                    .toList();
        }
    }

    @Builder
    public record PopularProduct(
            Long productId,
            String name,
            int rank,
            Long totalSelling
    ) {
        public static List<ProductResponse.PopularProduct> from(List<ProductInfo.PopularProduct> products){
            return  products.stream()
                    .map(product -> PopularProduct.builder()
                            .productId(product.productId())
                            .name(product.name())
                            .rank(product.rank())
                            .totalSelling(product.totalSelling())
                            .build())
                    .toList();
        }
    }
}