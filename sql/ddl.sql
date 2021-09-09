DROP TABLE orders;
DROP TABLE cart;
DROP TABLE product;
DROP TABLE users;

DROP SEQUENCE orders_SEQ;
DROP SEQUENCE cart_SEQ;
DROP SEQUENCE product_SEQ;
DROP SEQUENCE users_SEQ;

CREATE SEQUENCE orders_SEQ
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE users_SEQ
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE cart_SEQ
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE product_SEQ
START WITH 1
INCREMENT BY 1;


CREATE TABLE product
(
    product_idx     NUMBER           PRIMARY KEY,
    category		VARCHAR2(20)	 NOT NULL,
    product_name    VARCHAR2(50)     NOT NULL, 
    price           NUMBER           NOT NULL, 
    color           VARCHAR2(20)     NULL, 
    psize           VARCHAR2(20)     NULL
);

CREATE TABLE cart
(
    cart_idx       NUMBER          PRIMARY KEY, 
    user_id        VARCHAR2(20)    NOT NULL, 
    product_idx    NUMBER          NULL
);

CREATE TABLE orders
(
    order_idx      NUMBER          PRIMARY KEY,
    user_id        VARCHAR2(20)    NOT NULL, 
    product_idx    NUMBER          NOT NULL, 
    order_date     DATE            NOT NULL
);

CREATE TABLE users
(
    id         VARCHAR2(20)     PRIMARY KEY,
    pw         VARCHAR2(20)     NOT NULL, 
    admin	     NUMBER(1)		  	NOT NULL,
    name       VARCHAR2(20)     NOT NULL, 
    address    VARCHAR2(300)    NOT NULL, 
    phone      VARCHAR2(20)     NOT NULL 
);


ALTER TABLE orders  ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE orders  ADD FOREIGN KEY (product_idx) REFERENCES product(product_idx);
ALTER TABLE cart  ADD FOREIGN KEY (user_id) REFERENCES users(id);
ALTER TABLE cart  ADD FOREIGN KEY (product_idx) REFERENCES product(product_idx);