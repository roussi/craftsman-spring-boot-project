CREATE TABLE IF NOT EXISTS product (
    id smallserial,
    created_date TIMESTAMP,
    modified_date TIMESTAMP,
    product_ref VARCHAR(50) NOT NULL,
    product_label VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);