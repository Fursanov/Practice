CREATE VIEW product_reviews_details AS
SELECT pr.review_id, pr.product_id, p.name AS product_name, pr.customer_id, u.username AS user_username, pr.rating, pr.review_text, pr.review_date
FROM product_reviews pr
INNER JOIN products p ON pr.product_id = p.product_id
INNER JOIN users u ON pr.customer_id = u.user_id;

CREATE VIEW store_products AS
SELECT ps.store_id, s.store_name, ps.product_id, p.name AS product_name, p.description, p.price, p.stock_quantity
FROM product_stores ps
INNER JOIN stores s ON ps.store_id = s.store_id
INNER JOIN products p ON ps.product_id = p.product_id;
