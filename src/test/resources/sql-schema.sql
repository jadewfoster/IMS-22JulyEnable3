drop schema ims;

CREATE SCHEMA IF NOT EXISTS ims;
USE ims ;

CREATE TABLE IF NOT EXISTS ims.customers (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(40) DEFAULT NULL,
    surname VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ims.items (
	item_id INT NOT NULL AUTO_INCREMENT,
	item_name VARCHAR(40) DEFAULT NULL,
	item_price DOUBLE NOT NULL,
	item_stock INT,
	PRIMARY KEY(item_id)
);

CREATE TABLE IF NOT EXISTS ims.orders (
	order_id INT NOT NULL AUTO_INCREMENT,
	fk_customer_id INT NOT NULL,
	FOREIGN KEY (fk_customer_id) REFERENCES customers(id), 
	PRIMARY KEY(order_id)
);

-- AS ONE ORDER CAN CONTAIN SEVERAL ITEMS
CREATE TABLE IF NOT EXISTS ims.orders_items (
	orders_items_id INT NOT NULL AUTO_INCREMENT,
	fk_order_id INT NOT NULL,
    fk_item_id INT NOT NULL,
	quantity INT NOT NULL,
	PRIMARY KEY (orders_items_id), 
    FOREIGN KEY (fk_order_id) REFERENCES orders(order_id),
	FOREIGN KEY (fk_item_id) REFERENCES items(item_id)
);
