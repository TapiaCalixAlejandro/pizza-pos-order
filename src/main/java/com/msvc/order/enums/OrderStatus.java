package com.msvc.order.enums;

public enum OrderStatus {
    PENDING,
    PREPARING,
    READY,
    DELIVERED,
    CANCELLED;

    // Metodo para validar si un estado puede pasar a otro
    public boolean canTransactionTo(OrderStatus newStatus) {
        return switch (this) {
            case PENDING -> newStatus == PREPARING || newStatus == CANCELLED;
            case PREPARING -> newStatus == READY || newStatus == CANCELLED;
            case READY -> newStatus == DELIVERED || newStatus == CANCELLED;
            case DELIVERED -> false; // Una orden entregada ya no se puede mover
            case CANCELLED -> false; // Una orden cancelada es definitiva
        };
    }
}
