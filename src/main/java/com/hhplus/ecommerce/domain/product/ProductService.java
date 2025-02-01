package com.hhplus.ecommerce.domain.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductInfo.Item> product(Long brandId){
        return ProductInfo.Item.from(productRepository.findbyBrandId(brandId));
    }

    public List<ProductInfo.ItemStock> stock(Long productId){
        return ProductInfo.ItemStock.from(productRepository.findbyProductId(productId));
    }

    public List<ProductInfo.PopularProduct> popular(){
        return null;
    }
}
