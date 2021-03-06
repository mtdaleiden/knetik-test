USE knetik_test;

DROP TABLE IF EXISTS cart_item;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS shopping_cart;

CREATE TABLE item (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	currency VARCHAR(10) NOT NULL DEFAULT 'USD',
	unit_price DECIMAL(10,2) NOT NULL,
	description VARCHAR(80) NOT NULL);
	

CREATE TABLE shopping_cart (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	currency VARCHAR(10) NOT NULL DEFAULT 'USD');
	
CREATE TABLE cart_item (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	cart_id BIGINT NOT NULL,
	item_id BIGINT NOT NULL,
	quantity INT NOT NULL,
	unit_price DECIMAL(10,2) NOT NULL,
	INDEX(cart_id),
	INDEX(item_id),
	FOREIGN KEY (cart_id) REFERENCES shopping_cart(id),
	FOREIGN KEY (item_id) REFERENCES item(id));
	