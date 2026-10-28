CREATE TABLE orders(
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    payment_status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    order_number BIGINT NOT NULL UNIQUE,
    service_type VARCHAR(30) NOT NULL,
    customer_name VARCHAR(150),
    notes VARCHAR(500),
    subtotal NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
    tax NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
    discount NUMERIC(10, 2) NOT NULL,
    total NUMERIC(10, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

CREATE TABLE order_details(
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    product_name VARCHAR(150) NOT NULL,
    quantity INT NOT NULL,
    unit_price NUMERIC(10, 2) NOT NULL,
    discount NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
    subtotal NUMERIC(10, 2) NOT NULL,
    notes VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,

    CONSTRAINT fk_order_details_order
        FOREIGN KEY (order_id)
        REFERENCES orders(id)
        ON DELETE CASCADE
);

CREATE TABLE order_detail_ingredients(
    id BIGSERIAL PRIMARY KEY,
    order_detail_id BIGINT NOT NULL,
    ingredient_id BIGINT NOT NULL,
    action VARCHAR(20) NOT NULL,
    quantity NUMERIC(10, 3) NOT NULL,
    unit_price NUMERIC(10, 2) NOT NULL,

    CONSTRAINT fk_order_detail_ingredients_detail
        FOREIGN KEY (order_detail_id)
        REFERENCES order_details(id)
        ON DELETE CASCADE
);