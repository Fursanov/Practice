CREATE TABLE products (
    product_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INT NOT NULL,
    brand_id INT REFERENCES product_brands(brand_id)
);

CREATE TABLE product_brands (
    brand_id SERIAL PRIMARY KEY,
    brand_name VARCHAR(255) NOT NULL
);

CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(user_id),
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2) NOT NULL
);

CREATE TABLE order_items (
    order_item_id SERIAL PRIMARY KEY,
    order_id INT REFERENCES orders(order_id),
    product_id INT REFERENCES products(product_id),
    quantity INT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE stores (
    store_id SERIAL PRIMARY KEY,
    store_name VARCHAR(255) NOT NULL,
    location VARCHAR(255)
);

CREATE TABLE product_reviews (
    review_id SERIAL PRIMARY KEY,
    product_id INT REFERENCES products(product_id),
    customer_id INT REFERENCES user(user_id),
    rating INT NOT NULL,
    review_text TEXT,
    review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE product_stores (
    product_id INT REFERENCES products(product_id),
    store_id INT REFERENCES stores(store_id),
    PRIMARY KEY (product_id, store_id)
);


