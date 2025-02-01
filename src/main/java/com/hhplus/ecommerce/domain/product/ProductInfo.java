package com.hhplus.ecommerce.domain.product;

import com.hhplus.ecommerce.interfaces.dto.product.ProductResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record ProductInfo(
        List<ProductResponse.Product> products,
        List<ProductResponse.ProductStock> productStocks,
        List<ProductResponse.PopularProduct> popularProducts
) {
    @Builder
    public record Item(
            Long productId,
            Long brandId,
            String name
    ) {
        public static List<ProductInfo.Item> from(List<Product> products){
            return  products.stream()
                    .map(product -> Item.builder()
                            .productId(product.getId())
                            .brandId(product.getBrandId())
                            .name(product.getName())
                            .build())
                    .toList();
        }
    }
    @Builder
    public record ItemStock(
            Long stockId,
            Long productId,
            String sku,
            String name,
            Long quantity,
            Long price
    ) {
        public static List<ProductInfo.ItemStock> from(List<ProductStock> stocks){
            return  stocks.stream()
                    .map(stock -> ProductInfo.ItemStock.builder()
                            .stockId(stock.getId())
                            .productId(stock.getProductId())
                            .sku(stock.getSku())
                            .name(stock.getName())
                            .quantity(stock.getQuantity())
                            .price(stock.getPrice())
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
    ) {}
}
