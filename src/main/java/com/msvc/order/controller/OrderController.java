package com.msvc.order.controller;

import com.msvc.order.dto.order.request.OrderRequest;
import com.msvc.order.dto.order.request.UpdateOrderStatusRequest;
import com.msvc.order.dto.order.response.OrderResponse;
import com.msvc.order.enums.OrderStatus;
import com.msvc.order.service.OrderService;
import com.msvc.order.shared.constants.Messages;
import com.msvc.order.shared.response.ApiResponse;
import com.msvc.order.shared.response.ResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(
        name = "Orders",
        description = "Operations related to order management."
)
public class OrderController {

    private final ResponseFactory responseFactory;
    private final OrderService orderService;

    public OrderController(
            ResponseFactory responseFactory,
            OrderService orderService
    ) {
        this.responseFactory = responseFactory;
        this.orderService = orderService;
    }

    @Operation(
            summary = "Create an order",
            description = "Creates a new order with its products and calculates the order totals."
    )
    @ApiResponses(
            {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "Order created successfully."
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Validation error."
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Order not found."
                    )
            }
    )
    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> create(
            @Valid @RequestBody OrderRequest request
    ) {
        OrderResponse response = orderService.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        responseFactory.success(
                                Messages.ORDER_CREATED,
                                response
                        )
                );
    }

    @Operation(
            summary = "Get order by ID",
            description = "Returns an order by its identifier"
    )
    @ApiResponses(
            {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Order found successfully."
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Order not found."
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(
            @PathVariable Long id
    ) {
        OrderResponse response = orderService.getOrderById(id);

        return ResponseEntity
                .ok(
                        responseFactory.success(
                                Messages.ORDER_FOUND,
                                response
                        )
                );
    }

    @Operation(
            summary = "Get all orders",
            description = "Returns all active orders."
    )
    @ApiResponses(
            {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Orders retrieved successfully."
                    )
            }
    )
    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> findAll() {
        List<OrderResponse> responses = orderService.findAll();

        return ResponseEntity
                .ok(
                        responseFactory.success(
                                Messages.ORDERS_RETRIEVED,
                                responses
                        )
                );
    }

    @Operation(
            summary = "Get all by order status",
            description = "Returns all active all by orders status."
    )
    @ApiResponses(
            {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Orders retrieved successfully."
                    )
            }
    )
    @GetMapping("/status/{orderStatus}")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> findAllByOrderStatus(
            @PathVariable OrderStatus orderStatus
    ) {
        List<OrderResponse> responses = orderService.getByStatus(orderStatus);

        return ResponseEntity
                .ok(
                        responseFactory.success(
                                Messages.ORDERS_RETRIEVED,
                                responses
                        )
                );
    }

    @Operation(
            summary = "Update order status",
            description = "Changes the status of an existing order (e.g., PENDING to PREPARING)."
    )
    @ApiResponses(
            {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Order state updated successfully."
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "400",
                            description = "Validation error."
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Order not found."
                    )
            }
    )
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<OrderResponse>> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrderStatusRequest request
    ) {
        OrderResponse response = orderService.updateOrderStatus(id, request.getStatus());

        return ResponseEntity
                .ok(
                        responseFactory.success(
                                Messages.ORDER_STATUS_UPDATE,
                                response
                        )
                );
    }

    @Operation(
            summary = "Cancel an order",
            description = "Soft-deletes an order and changes its status to CANCELLED."
    )
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<Void>> cancelOrder(
            @PathVariable Long id
    ) {
        orderService.cancelOrder(id);

        return ResponseEntity
                .ok(
                        responseFactory.success(
                                Messages.ORDER_CANCELLED,
                                null
                        )
                );
    }

}
