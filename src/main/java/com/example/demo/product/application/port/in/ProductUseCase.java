package com.example.demo.product.application.port.in;

import com.example.demo.product.domain.Product;
import com.example.demo.product.dto.in.ProductCreateRequest;
import com.example.demo.product.dto.in.ProductUpdateRequest;
import com.example.demo.product.dto.out.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductUseCase {

    ProductResponse create(ProductCreateRequest request);

    Product getById(UUID productId);

    List<ProductResponse> getAll();

    Product update(UUID productId, ProductUpdateRequest request);

    void delete(UUID productId);
}
