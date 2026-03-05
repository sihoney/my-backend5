package com.example.demo.product.adaptor.in.web;

import com.example.demo.product.domain.Product;
import com.example.demo.product.dto.in.ProductCreateRequest;
import com.example.demo.product.dto.in.ProductUpdateRequest;
import com.example.demo.product.application.port.in.ProductUseCase;
import com.example.demo.product.dto.out.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@Tag(name = "", description = "상품 CRUD API") // @Tag: API 그룹을 묶는 역할, 예) 상품 관련 API
public class ProductController {

    @Autowired
    private ProductUseCase productUseCase;

//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }

//     의존성 주입 방식
//     1. autowired
//     2. requiredArgsConstructor
//     3. 생성자 메서드

    @GetMapping
    public List<ProductResponse> getAll() {
        return productUseCase.getAll();
    }

    @PostMapping
    @Operation(summary = "상품 생성", description = "") //@Operation: 개별 API 엔드포인트(메서드 단위)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "생성 성공",
                    content = @Content(
                            schema = @Schema(implementation = Product.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "요청 값 오류"
            )
    }) // @ApiResponse: 응답 코드에 대해 어떤 응답 데이터가 나가는지 명시
    public ResponseEntity<ProductResponse> create(@RequestBody ProductCreateRequest request) {
        ProductResponse response = productUseCase.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
//    @PostMapping
//    public ResponseEntity<Product> create(@RequestBody ProductCreateRequest request) {
//        Product response = productService.create(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }

    @GetMapping("/{productId}")
    public Product getById(@PathVariable UUID productId) {
        return productUseCase.getById(productId);
    }

    @PutMapping("/{productId}")
    public Product update(@PathVariable UUID productId,
                          @RequestBody ProductUpdateRequest request) {
        return productUseCase.update(productId, request);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable UUID productId) {
        productUseCase.delete(productId);
        return ResponseEntity.noContent().build();
    }
}
