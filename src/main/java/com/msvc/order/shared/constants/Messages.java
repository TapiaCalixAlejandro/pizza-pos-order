package com.msvc.order.shared.constants;

public final class Messages {

    private Messages() {}

    public static final String ORDER_MUST_HAVE_DETAILS = "The order must contain at least one product.";
    public static final String UNIT_PRICE_MUST_BE_POSITIVE = "The unit price must be greater than zero.";
    public static final String QUANTITY_MUST_BE_POSITIVE = "The quantity must be greater than zero.";
    public static final String DETAIL_DISCOUNT_CANNOT_BE_NEGATIVE = "The product discount cannot be negative.";
    public static final String DETAIL_DISCOUNT_EXCEEDS_SUBTOTAL = "The product discount cannot be greater than its gross subtotal.";
    public static final String ORDER_DISCOUNT_CANNOT_BE_NEGATIVE = "The overall discount cannot be negative.";
    public static final String TOTAL_DISCOUNTS_EXCEED_SUBTOTAL = "The total discount cannot exceed the order subtotal.";

    public static final String ORDER_CREATED = "Order created successfully.";
    public static final String ORDER_FOUND = "Order retrieved successfully.";
    public static final String ORDER_NOT_FOUND = "Order not found.";
    public static final String ORDERS_RETRIEVED = "Orders retrieved successfully.";

    public static final String ORDER_STATUS_UPDATE = "Order status updated";

}
