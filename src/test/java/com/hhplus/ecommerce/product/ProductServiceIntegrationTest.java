package com.hhplus.ecommerce.product;

import com.hhplus.ecommerce.domain.product.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Nested
    @DisplayName("상품")
    class BrandProduct
    {
        @Test
        @DisplayName("[성공]상품목록조회")
        void 상품목록조회(){
            Long brandId = 3L;

            List<Product> products = List.of(
                    Product.builder().brandId(3L).name("상품1").build(),
                    Product.builder().brandId(3L).name("상품2").build(),
                    Product.builder().brandId(4L).name("상품3").build()
            );
            productRepository.saveAllProduct(products);

            List<ProductInfo.Item> resultProduct = productService.product(brandId);

            assertThat(resultProduct).isNotEmpty();
            assertThat(resultProduct.size()).isEqualTo(2);

            assertThat(resultProduct.get(0).productId()).isEqualTo(products.get(0).getId());
            assertThat(resultProduct.get(0).brandId()).isEqualTo(products.get(0).getBrandId());

            assertThat(resultProduct.get(1).productId()).isEqualTo(products.get(1).getId());
            assertThat(resultProduct.get(1).brandId()).isEqualTo(products.get(1).getBrandId());
        }

        @Test
        @DisplayName("[실패]_존재하지않는_brandId")
        void 존재하지않는_brandId(){
            Long brandId = 5L;

            List<Product> products = List.of(
                    Product.builder().brandId(3L).name("상품1").build(),
                    Product.builder().brandId(3L).name("상품2").build(),
                    Product.builder().brandId(4L).name("상품3").build()
            );
            productRepository.saveAllProduct(products);

            List<ProductInfo.Item> resultProduct = productService.product(brandId);

            assertThat(resultProduct).isEmpty();
        }
    }

    @Nested
    @DisplayName("재고")
    class Stock
    {
        @Test
        @DisplayName("[성공]상품옵션별_재고조회")
        void 상품옵션별_재고조회(){
            Long productId = 3L;

            List<ProductStock> stocks = List.of(
                    ProductStock.builder()
                            .productId(3L).sku("SKU001").name("상품1_옵션1").quantity(10L).price(10000L).build(),
                    ProductStock.builder()
                            .productId(3L).sku("SKU002").name("상품1_옵션2").quantity(11L).price(11000L).build(),
                    ProductStock.builder()
                            .productId(4L).sku("SKU003").name("상품2_옵션3").quantity(12L).price(12000L).build()
            );
            productRepository.saveAllProductStock(stocks);

            List<ProductInfo.ItemStock> resultProductStock = productService.stock(productId);

            assertThat(resultProductStock).isNotEmpty();
            assertThat(resultProductStock.size()).isEqualTo(2);

            assertThat(resultProductStock.get(0).productId()).isEqualTo(stocks.get(0).getProductId());
            assertThat(resultProductStock.get(0).sku()).isEqualTo(stocks.get(0).getSku());
            assertThat(resultProductStock.get(0).price()).isEqualTo(stocks.get(0).getPrice());

            assertThat(resultProductStock.get(1).productId()).isEqualTo(stocks.get(1).getProductId());
            assertThat(resultProductStock.get(1).sku()).isEqualTo(stocks.get(1).getSku());
            assertThat(resultProductStock.get(1).price()).isEqualTo(stocks.get(1).getPrice());
        }

        @Test
        @DisplayName("[실패]_존재하지않는_productId")
        void 존재하지않는_productId(){
            Long productId = 5L;

            List<ProductStock> stocks = List.of(
                    ProductStock.builder()
                            .productId(3L).sku("SKU001").name("상품1_옵션1").quantity(10L).price(10000L).build(),
                    ProductStock.builder()
                            .productId(3L).sku("SKU002").name("상품1_옵션2").quantity(11L).price(11000L).build(),
                    ProductStock.builder()
                            .productId(4L).sku("SKU003").name("상품2_옵션3").quantity(12L).price(12000L).build()
            );
            productRepository.saveAllProductStock(stocks);

            List<ProductInfo.ItemStock> resultProductStock = productService.stock(productId);

            assertThat(resultProductStock).isEmpty();
        }

    }
}
