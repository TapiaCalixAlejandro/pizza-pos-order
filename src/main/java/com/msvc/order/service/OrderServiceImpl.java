package com.msvc.order.service;

import com.msvc.order.dto.order.request.OrderRequest;
import com.msvc.order.dto.order.response.OrderResponse;
import com.msvc.order.dto.orderdetail.request.OrderDetailRequest;
import com.msvc.order.dto.orderdetailingredient.request.OrderDetailIngredientRequest;
import com.msvc.order.entity.Order;
import com.msvc.order.entity.OrderDetail;
import com.msvc.order.entity.OrderDetailIngredient;
import com.msvc.order.enums.OrderStatus;
import com.msvc.order.enums.PaymentStatus;
import com.msvc.order.mapper.OrderDetailIngredientMapper;
import com.msvc.order.mapper.OrderDetailMapper;
import com.msvc.order.mapper.OrderMapper;
import com.msvc.order.repository.OrderRepository;
import com.msvc.order.shared.exception.BusinessException;
import com.msvc.order.shared.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    private static final BigDecimal TAX_RATE = new BigDecimal("0.16");

    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final OrderDetailIngredientMapper detailIngredientMapper;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(
            OrderMapper orderMapper,
            OrderDetailMapper orderDetailMapper,
            OrderDetailIngredientMapper detailIngredientMapper,
            OrderRepository orderRepository
    ) {
        this.orderMapper = orderMapper;
        this.orderDetailMapper = orderDetailMapper;
        this.detailIngredientMapper = detailIngredientMapper;
        this.orderRepository = orderRepository;
    }

    @Transactional
    @Override
    public OrderResponse create(OrderRequest request) {
        LOGGER.info(
                "Creating new order [serviceType={}]",
                request.getServiceType()
        );

        // Mapear el Request a Entidad
        Order order = orderMapper.toEntity(request);

        // Inicializar valores generados por el sistema
        order.setOrderNumber(orderRepository.getNextOrderNumber());
        order.setStatus(OrderStatus.PENDING);
        order.setPaymentStatus(PaymentStatus.PENDING);

        // Validar y procesar los detalles (logica de precios)
        processOrderDetails(order, request);

        // Calcular totales generales (Impuestos y descuentos)
        calculateOrderTotals(order, request.getDiscount());

        // Guardar en la base de datos
        Order savedOrder = orderRepository.save(order);

        LOGGER.info(
                "Order created successfully [id={}, orderNumber={}]",
                savedOrder.getId(),
                savedOrder.getOrderNumber()
        );

        return orderMapper.toResponse(savedOrder);
    }

    @Transactional(readOnly = true)
    @Override
    public OrderResponse getOrderById(Long id) {
        LOGGER.info(
                "Getting order [id={}]",
                id
        );

        return orderRepository.findByIdAndDeletedAtIsNull(id)
                .map(orderMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found."));
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderResponse> findAll() {
        LOGGER.info("Getting all orders");
        List<Order> responses = orderRepository.findAllByDeletedAtIsNull();

        return orderMapper.toResponseList(responses);
    }

    @Override
    public List<OrderResponse> getByStatus(OrderStatus status) {
        return List.of();
    }

    @Override
    public OrderResponse updateOrderStatus(Long id, OrderStatus status) {
        return null;
    }

    @Override
    public OrderResponse updatePaymentStatus(Long id, PaymentStatus newPaymentStatus) {
        return null;
    }

    @Override
    public void cancelOrder(Long id) {

    }

    /**
     * Procesa cada linea de la orden, valida precios, calcula subtotales
     * y establece las relaciones bidireccionales usando metodos de dominio.
     */
    private void processOrderDetails(Order order, OrderRequest request) {
        if (request.getOrderDetails() == null || request.getOrderDetails().isEmpty()) {
            throw new BusinessException("La orden debe tener al menos un producto.");
        }

        for (OrderDetailRequest detailRequest : request.getOrderDetails()) {
            // Mapear el request del detalle a Entidad (las listas se ignoran en el mapper)
            OrderDetail detail = orderDetailMapper.toEntity(detailRequest);

            // Validaciones basicas
            if (detail.getUnitPrice() == null || detail.getUnitPrice().compareTo(BigDecimal.ZERO) <= 0) {
                throw new BusinessException("El precio unitario debe ser mayor a cero.");
            }

            if (detail.getQuantity() == null || detail.getQuantity() <= 0) {
                throw new BusinessException("La cantidad debe ser mayor a cero.");
            }

            // Descuento de linea
            BigDecimal detailDiscount = detail.getDiscount() != null
                    ? detail.getDiscount()
                    : BigDecimal.ZERO;

            if (detailDiscount.compareTo(BigDecimal.ZERO) < 0) {
                throw new BusinessException("El descuento del producto no puede ser negativo.");
            }

            BigDecimal grossLineTotal = detail.getUnitPrice().multiply(BigDecimal.valueOf(detail.getQuantity()));
            if (detailDiscount.compareTo(grossLineTotal) > 0) {
                throw new BusinessException("El descuento del producto no puede ser mayor a su subtotal bruto.");
            }

            // Guardar valores en la entidad
            detail.setDiscount(detailDiscount);
            detail.setSubtotal(grossLineTotal.setScale(2, RoundingMode.HALF_UP));

            // Manejo de ingredientes (Mapear y usar metodo de dominio)
            if (detailRequest.getDetailIngredients() != null) {
                for (OrderDetailIngredientRequest detailIngredient : detailRequest.getDetailIngredients()) {
                    OrderDetailIngredient ingredient = detailIngredientMapper.toEntity(detailIngredient);
                    detail.addDetailIngredient(ingredient);
                }
            }

            // Usar el metodo de dominio para enlazar la orden
            order.addOrderDetail(detail);
        }
    }

    /**
     * Calcula el descuento general, impuestos y total final.
     * Asume que Order.subtotal guarda el bruto, y Order.discount guarda el descuento general.
     */
    private void calculateOrderTotals(Order order, BigDecimal requestDiscount) {
        // Calcular subtotal bruto (sin restar descuentos de linea)
        BigDecimal grossSubtotal = order.getOrderDetails()
                .stream()
                .map(OrderDetail::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Calcular total de descuentos por linea
        BigDecimal totalLineDiscounts = order.getOrderDetails()
                .stream()
                .map(OrderDetail::getDiscount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Descuento general (a nivel orden)
        BigDecimal generalDiscount = requestDiscount != null
                ? requestDiscount
                : BigDecimal.ZERO;

        if (generalDiscount.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("El descuento general no puede ser negativo.");
        }

        // Base gravable = Bruto - Descuentos de linea - Descuento General
        BigDecimal taxableBase = grossSubtotal.subtract(totalLineDiscounts).subtract(generalDiscount);
        if (taxableBase.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Los descuentos totales no pueden superar el subtotal de la orden.");
        }

        // Calcular IVA sobre base gravable
        BigDecimal tax = taxableBase.multiply(TAX_RATE).setScale(2, RoundingMode.HALF_UP);

        // Total final
        BigDecimal total = taxableBase.add(tax).setScale(2, RoundingMode.HALF_UP);

        // Asignar en la entidad
        order.setSubtotal(grossSubtotal.setScale(2, RoundingMode.HALF_UP));
        order.setDiscount(generalDiscount);
        order.setTax(tax);
        order.setTotal(total);
    }

}
