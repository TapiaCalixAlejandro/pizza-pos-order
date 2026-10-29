package com.msvc.order.client;

import com.msvc.order.client.dto.ProductResponse;
import com.msvc.order.shared.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-catalog", url = "${app.catalog.url}")
public interface CatalogClient {

    @GetMapping("/api/products/{id}")
    ApiResponse<ProductResponse> getProductById(@PathVariable Long id);

}
