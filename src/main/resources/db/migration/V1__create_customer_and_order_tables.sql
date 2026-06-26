-- ==========================================
-- DATABASE
-- ==========================================

CREATE DATABASE IF NOT EXISTS customer_service_db;

USE customer_service_db;

-- ==========================================
-- TABLE: CUSTOMERS
-- ==========================================

CREATE TABLE customers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20),
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
);

-- ==========================================
-- TABLE: ORDERS
-- ==========================================

CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    order_number VARCHAR(50) NOT NULL UNIQUE,
    amount DECIMAL(12,2) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'CREATED',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_orders_customer
        FOREIGN KEY (customer_id)
        REFERENCES customers(id)
        ON DELETE CASCADE
);

-- ==========================================
-- INDEXES
-- ==========================================

CREATE UNIQUE INDEX idx_customers_email
ON customers(email);

CREATE INDEX idx_orders_customer_id
ON orders(customer_id);

CREATE INDEX idx_orders_status
ON orders(status);

-- ==========================================
-- TEST DATA
-- ==========================================

INSERT INTO customers (first_name, last_name, email, phone, status)
VALUES
('Luis', 'Osis', 'luis@test.com', '999888777', 'ACTIVE'),
('Ana', 'Garcia', 'ana@test.com', '999111222', 'ACTIVE');

INSERT INTO orders (customer_id, order_number, amount, status)
VALUES
(1, 'ORD-1001', 150.00, 'CREATED'),
(1, 'ORD-1002', 300.00, 'PAID'),
(2, 'ORD-1003', 75.50, 'CREATED');