INSERT INTO product VALUES(product_SEQ.NEXTVAL, '상의', '캐시미어니트', 70000, 'grey', 'free');
INSERT INTO product VALUES(product_SEQ.NEXTVAL, '하의', '조거팬츠', 38000, 'black', 'free');

INSERT INTO users VALUES('admin', 'adminpw', '1', '관리자', '관리자집', '01012345678');
INSERT INTO users VALUES('geesuee', 'jisu', '0', '배지수', '인천 중구 하늘별빛로 121', '01030576577');
INSERT INTO users VALUES('noowah', 'noowah', '0', '조하운', '서울 은평구 불광로 16길 25-7 2층', '01044460410');
INSERT INTO users VALUES('paparo2', 'papa', '0', '김한나', '경기도 삼평동 동판교로 212', '01066000028');


INSERT INTO cart VALUES(cart_SEQ.NEXTVAL, 'noowah', 1);

INSERT INTO orders VALUES(orders_SEQ.NEXTVAL, 'noowah', 1, '2021-09-06');

commit;