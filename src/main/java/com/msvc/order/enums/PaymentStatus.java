package com.msvc.order.enums;

public enum PaymentStatus {
    PENDING,
    PAID,
    FAILED,
    REFUNDED;

    public boolean canTransitionTo(PaymentStatus newStatus) {
        return switch (this) {
            case PENDING -> newStatus == PAID || newStatus == FAILED;
            case FAILED -> newStatus == PENDING || newStatus == PAID; // Reintento
            case PAID -> newStatus == REFUNDED;
            case REFUNDED -> false; // Estado final
        };
    }
}
