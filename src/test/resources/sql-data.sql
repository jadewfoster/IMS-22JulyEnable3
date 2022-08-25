INSERT INTO ims.customers (first_name, surname) VALUES 
('sarah', 'smith'),
('louise', 'connelly'),
('liam', 'miller'),
('emily', 'wilson'),
('rebecca', 'jones'),
('robert', 'taylor'),
('richard', 'johnson'),
('dakota', 'lee'),
('harry', 'campbell'),
('kim', 'moore');

INSERT INTO ims.items(item_name, item_price, item_stock) VALUES 
('Espresso Martini', 7.80, 100),
('Dirty Martini', 9.10, 100),
('Pina Colada', 9.20, 100),
('Daiquiri', 8.60, 100),
('Mojito', 8.90, 100),
('Passionfruit Martini', 8.60, 100),
('Aperol Spritz', 7.00, 100),
('Tequila Sunrise', 7.80, 100),
('Long Island Iced Tea', 7.30, 100),
('Mimosa', 6.30, 100),
('Mango Bellini', 6.50, 100),
('Sangria', 6.70, 100),
('Margarita', 8.10, 100),
('Woo Woo', 7.60, 100),
('Negroni', 6.90, 100);

INSERT INTO ims.orders(fk_customer_id) VALUES 
(1),
(1),
(2),
(3),
(4),
(4),
(4),
(5),
(5),
(6),
(7),
(8),
(9),
(10);

INSERT INTO ims.orders_items(fk_order_id, fk_item_id, quantity) VALUES
(1, 6, 1),
(1, 5, 2),
(1, 10, 1),
(1, 13, 1),
(2, 1, 3),
(2, 3, 1),
(3, 5, 2),
(4, 6, 2),
(4, 7, 1),
(4, 8, 1),
(4, 10, 1),
(5, 8, 4),
(6, 10, 1),
(7, 2, 1),
(8, 3, 3),
(9, 9, 1),
(9, 4, 2),
(10, 3, 1),
(10, 6, 3);