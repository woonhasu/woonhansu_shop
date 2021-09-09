INSERT INTO product VALUES(product_SEQ.NEXTVAL, 'bottom', '코튼팬츠', 40000, 'beige', 'free');
INSERT INTO product VALUES(product_SEQ.NEXTVAL, 'setup', '올블랙 셋업', 68000, 'black', 'free');
INSERT INTO product VALUES(product_SEQ.NEXTVAL, 'top', '스트라이프 셔츠', 34000, 'blue', 'free');
INSERT INTO product VALUES(product_SEQ.NEXTVAL, 'outer', '숏카라 코트', 48000, 'blue', 'free');
INSERT INTO product VALUES(product_SEQ.NEXTVAL, 'top', '후드 아노락', 36000, 'khaki', 'free');
INSERT INTO product VALUES(product_SEQ.NEXTVAL, 'top', '그레이 후드티', 30000, 'grey', 'free');
INSERT INTO product VALUES(product_SEQ.NEXTVAL, 'bottom', '베이지 코튼 스커트', 32000, 'beige', 'free');

INSERT INTO users VALUES('admin', 'adminpw', '1', '관리자', '관리자집', '01012345678');
INSERT INTO users VALUES('geesuee', 'jisu', '0', '배지수', '인천 중구 하늘별빛로 121', '01030576577');
INSERT INTO users VALUES('noowah', 'noowah', '0', '조하운', '서울 은평구 불광로 16길 25-7 2층', '01044460410');
INSERT INTO users VALUES('paparo2', 'papa', '0', '김한나', '경기도 삼평동 동판교로 212', '01066000028');


INSERT INTO cart VALUES(cart_SEQ.NEXTVAL, 'noowah', 1);

INSERT INTO orders VALUES(orders_SEQ.NEXTVAL, 'noowah', 1, '2021-09-06');

commit;