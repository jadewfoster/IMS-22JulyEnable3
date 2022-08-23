drop schema ims;

CREATE SCHEMA IF NOT EXISTS ims;

USE ims ;

CREATE TABLE IF NOT EXISTS ims.customers (
    customer_id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(40) DEFAULT NULL,
    surname VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (customer_id)
);

CREATE TABLE IF NOT EXISTS ims.items (
item_id INT NOT NULL AUTO_INCREMENT,
item_name VARCHAR(40) DEFAULT NULL,
item_price VARCHAR(40) DEFAULT NULL,
PRIMARY KEY(item_id)
);

CREATE TABLE IF NOT EXISTS ims.orders (
order_id INT NOT NULL AUTO_INCREMENT,
customer_id INT NOT NULL,
 FOREIGN KEY (customer_id) REFERENCES customers(customer_id), 
PRIMARY KEY(order_id)
);

-- AS ONE ORDER CAN CONTAIN SEVERAL ITEMS
CREATE TABLE IF NOT EXISTS ims.orders_items(
  item_id INT NOT NULL,
  order_id INT NOT NULL,
  quantity INT NOT NULL,
   FOREIGN KEY (item_id) REFERENCES items(item_id), 
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    UNIQUE (item_id, order_id)
);
