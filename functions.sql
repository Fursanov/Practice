CREATE OR REPLACE FUNCTION get_products_by_order_id(order_id INT) 
RETURNS TABLE (
    product_id INT,
    name VARCHAR(255),
    quantity INT,
    unit_price DECIMAL(10, 2)
) AS $$
BEGIN
    RETURN QUERY
    SELECT oi.product_id, p.name, oi.quantity, oi.unit_price
    FROM order_items oi
    INNER JOIN products p ON oi.product_id = p.product_id
    WHERE oi.order_id = order_id;
END;
$$ LANGUAGE plpgsql;




CREATE OR REPLACE FUNCTION calculate_total_revenue() 
RETURNS DECIMAL(10, 2) AS $$
DECLARE
    total_amount DECIMAL(10, 2);
BEGIN
    SELECT SUM(total_amount) INTO total_amount
    FROM orders;
    
    RETURN total_amount;
END;
$$ LANGUAGE plpgsql;




CREATE OR REPLACE FUNCTION get_top_5_popular_products() 
RETURNS TABLE (
    product_id INT,
    name VARCHAR(255),
    total_quantity INT,
    total_sales DECIMAL(10, 2)
) AS $$
BEGIN
    RETURN QUERY
    SELECT p.product_id, p.name, SUM(oi.quantity) AS total_quantity, SUM(oi.quantity * oi.unit_price) AS total_sales
    FROM order_items oi
    INNER JOIN products p ON oi.product_id = p.product_id
    GROUP BY p.product_id, p.name
    ORDER BY total_quantity DESC
    LIMIT 5;
END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION get_customer_purchases(user_id INT) 
RETURNS TABLE (
    order_id INT,
    order_date TIMESTAMP,
    total_amount DECIMAL(10, 2),
    product_id INT,
    product_name VARCHAR(255),
    quantity INT,
    unit_price DECIMAL(10, 2)
) AS $$
BEGIN
    RETURN QUERY
    SELECT o.order_id, o.order_date, o.total_amount, oi.product_id, p.name AS product_name, oi.quantity, oi.unit_price
    FROM orders o
    INNER JOIN order_items oi ON o.order_id = oi.order_id
    INNER JOIN products p ON oi.product_id = p.product_id
    WHERE o.user_id = user_id
    ORDER BY o.order_id;
END;
$$ LANGUAGE plpgsql;




CREATE OR REPLACE FUNCTION add_product(
    name VARCHAR(255),
    description TEXT,
    price DECIMAL(10, 2),
    stock_quantity INT,
    brand_id INT
) RETURNS VOID AS $$
BEGIN
    INSERT INTO products (name, description, price, stock_quantity, brand_id)
    VALUES (name, description, price, stock_quantity, brand_id);
END;
$$ LANGUAGE plpgsql;




CREATE OR REPLACE FUNCTION add_product_brand(
    brand_name VARCHAR(255)
) RETURNS VOID AS $$
BEGIN
    INSERT INTO product_brands (brand_name)
    VALUES (brand_name);
END;
$$ LANGUAGE plpgsql;




CREATE OR REPLACE FUNCTION add_user(
    username VARCHAR(255),
    email VARCHAR(255),
    password_hash VARCHAR(255),
    role VARCHAR(50)
) RETURNS VOID AS $$
BEGIN
    INSERT INTO users (username, email, password_hash, role)
    VALUES (username, email, password_hash, role);
END;
$$ LANGUAGE plpgsql;




CREATE OR REPLACE FUNCTION add_order(
    user_id INT
) RETURNS VOID AS $$
DECLARE
    order_id INT;
BEGIN
    INSERT INTO orders (user_id, total_amount)
    VALUES (user_id, 0);
END;
$$ LANGUAGE plpgsql;






CREATE OR REPLACE FUNCTION add_order_item(
    order_id INT,
    product_id INT,
    quantity INT,
    unit_price DECIMAL(10, 2)
) RETURNS VOID AS $$
BEGIN
    INSERT INTO order_items (order_id, product_id, quantity, unit_price)
    VALUES (order_id, product_id, quantity, unit_price);

    UPDATE orders
    SET total_amount = total_amount + (quantity * unit_price)
    WHERE order_id = order_id;
END;
$$ LANGUAGE plpgsql;









CREATE OR REPLACE FUNCTION delete_order_item(
    order_item_id INT
) RETURNS VOID AS $$
DECLARE
    order_id INT;
    item_total DECIMAL(10, 2);
BEGIN
    SELECT oi.order_id, oi.quantity * oi.unit_price INTO order_id, item_total
    FROM order_items oi
    WHERE oi.order_item_id = order_item_id;

    DELETE FROM order_items
    WHERE order_item_id = order_item_id;

    UPDATE orders
    SET total_amount = total_amount - item_total
    WHERE order_id = order_id;
END;
$$ LANGUAGE plpgsql;








CREATE OR REPLACE FUNCTION update_order_item_quantity(
    order_item_id INT,
    new_quantity INT
) RETURNS VOID AS $$
DECLARE
    old_quantity INT;
    unit_price DECIMAL(10, 2);
    order_id INT;
    item_total DECIMAL(10, 2);
BEGIN
    SELECT quantity, unit_price, order_id
    INTO old_quantity, unit_price, order_id
    FROM order_items
    WHERE order_item_id = order_item_id;

    item_total := old_quantity * unit_price;

    UPDATE order_items
    SET quantity = new_quantity
    WHERE order_item_id = order_item_id;

    UPDATE orders
    SET total_amount = total_amount - item_total + (new_quantity * unit_price)
    WHERE order_id = order_id;
END;
$$ LANGUAGE plpgsql;






CREATE OR REPLACE FUNCTION add_store(
    store_name VARCHAR(255),
    location VARCHAR(255)
) RETURNS VOID AS $$
BEGIN
    INSERT INTO stores (store_name, location)
    VALUES (store_name, location);
END;
$$ LANGUAGE plpgsql;






CREATE OR REPLACE FUNCTION add_product_review(
    product_id INT,
    customer_id INT,
    rating INT,
    review_text TEXT
) RETURNS VOID AS $$
BEGIN
    INSERT INTO product_reviews (product_id, customer_id, rating, review_text)
    VALUES (product_id, customer_id, rating, review_text);
END;
$$ LANGUAGE plpgsql;






CREATE OR REPLACE FUNCTION add_product_store(
    product_id INT,
    store_id INT
) RETURNS VOID AS $$
BEGIN
    INSERT INTO product_stores (product_id, store_id)
    VALUES (product_id, store_id);
END;
$$ LANGUAGE plpgsql;
