INSERT INTO product VALUES(product_SEQ.NEXTVAL, '머리띠', 3000, 'grey', 'free');
INSERT INTO product VALUES(product_SEQ.NEXTVAL, '몬스터(24개입)', 24000, null, null);

INSERT INTO users VALUES('geesuee', 'pw1', '배지수', '인천 중구 중산동 하늘별빛로 121', '01030576577');
INSERT INTO users VALUES('noowah', 'noowah', '조하운', '서울 은평구 불광로 16길 25-7 2층', '01044460410');
INSERT INTO users VALUES('paparo2', 'papa', '김한나', '경기도 삼평동 동판교로 212', '01066000028');


INSERT INTO cart VALUES(cart_SEQ.NEXTVAL, 'noowah', 1);
INSERT INTO orders VALUES(orders_SEQ.NEXTVAL, 'noowah', 1, '2021-09-06');

commit;

select * from orders;