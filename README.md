# woonhasu_shop

## 🛍 운하수 쇼핑

운하수 쇼핑은 `회원`,`제품`,`장바구니`,`주문내역` CRUD를 구현한 온라인 쇼핑 서비스 입니다. 

- 회원가입
- 회원정보수정
- 회원탈퇴
- 로그인(유저/관리자)
- 로그아웃
- 상품카트저장/삭제
- 상품정보추가/수정/삭제
- 단일상품주문/삭제
- 전체상품주문
- 회원정보확인


## 👨‍👧‍👧 **Team**
### [배지수](https://github.com/geesuee)

### [조하운](https://github.com/henrynoowah)

### [김한나](https://github.com/aNnaHmiK)

[woonhasu](https://github.com/woonhasu)


## ⚙ 개발 환경
- `Java` : 1.8 version
- `Eclipse` : photon
- `JPA`
- `Tomcat`
- `Servlet` / `JSP`
- `HTML` / `CSS` / `JavaScript`


# 2. SQL
- DDL

    ```sql
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
        admin	     NUMBER(1)		NOT NULL,
        name       VARCHAR2(20)     NOT NULL, 
        address    VARCHAR2(300)    NOT NULL, 
        phone      VARCHAR2(20)     NOT NULL 
    );

    ALTER TABLE orders  ADD FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;
    ALTER TABLE orders  ADD FOREIGN KEY (product_idx) REFERENCES product(product_idx) ON DELETE CASCADE;
    ALTER TABLE cart  ADD FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;
    ALTER TABLE cart  ADD FOREIGN KEY (product_idx) REFERENCES product(product_idx) ON DELETE CASCADE;
    ```

- DML

    ```sql
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
    ```

# 3. Directory Tree

- project 디렉토리 트리

    ```html
    C:.
    │  .classpath
    │  .gitignore
    │  .project
    │  pom.xml
    │
    ├─.settings
    │      .jsdtscope
    │      org.eclipse.jdt.core.prefs
    │      org.eclipse.jpt.core.prefs
    │      org.eclipse.m2e.core.prefs
    │      org.eclipse.wst.common.component
    │      org.eclipse.wst.common.project.facet.core.prefs.xml
    │      org.eclipse.wst.common.project.facet.core.xml
    │      org.eclipse.wst.jsdt.ui.superType.container
    │      org.eclipse.wst.jsdt.ui.superType.name
    │      org.eclipse.wst.validation.prefs
    │
    ├─build
    │  └─classes
    │      └─META-INF
    │              persistence.xml
    │
    ├─sql
    │      ddl.sql
    │      dml.sql
    │
    ├─src
    │  ├─controller
    │  │      Controller.java
    │  │
    │  ├─exception
    │  │      MessageException.java
    │  │      NotExistException.java
    │  │
    │  ├─META-INF
    │  │      persistence.xml
    │  │
    │  ├─model
    │  │  ├─DAO
    │  │  │      CartDAO.java
    │  │  │      OrdersDAO.java
    │  │  │      ProductDAO.java
    │  │  │      Service.java
    │  │  │      UsersDAO.java
    │  │  │
    │  │  ├─domain
    │  │  │      Cart.java
    │  │  │      Orders.java
    │  │  │      Product.java
    │  │  │      Users.java
    │  │  │
    │  │  └─DTO
    │  │          CartDTO.java
    │  │          OrdersDTO.java
    │  │          ProductDTO.java
    │  │          UsersDTO.java
    │  │
    │  └─util
    │          DBUtil.java
    │
    ├─target
    │  ├─classes
    │  │  ├─controller
    │  │  │      Controller.class
    │  │  │
    │  │  ├─exception
    │  │  │      MessageException.class
    │  │  │      NotExistException.class
    │  │  │
    │  │  ├─META-INF
    │  │  │      persistence.xml
    │  │  │
    │  │  ├─model
    │  │  │  ├─DAO
    │  │  │  │      CartDAO.class
    │  │  │  │      OrdersDAO.class
    │  │  │  │      ProductDAO.class
    │  │  │  │      Service.class
    │  │  │  │      UsersDAO.class
    │  │  │  │
    │  │  │  ├─domain
    │  │  │  │      Cart.class
    │  │  │  │      Orders.class
    │  │  │  │      Product$ProductBuilder.class
    │  │  │  │      Product.class
    │  │  │  │      Users$UsersBuilder.class
    │  │  │  │      Users.class
    │  │  │  │
    │  │  │  └─DTO
    │  │  │          CartDTO$Get.class
    │  │  │          CartDTO.class
    │  │  │          OrdersDTO$Create.class
    │  │  │          OrdersDTO$Get.class
    │  │  │          OrdersDTO.class
    │  │  │          ProductDTO$Create.class
    │  │  │          ProductDTO$Get.class
    │  │  │          ProductDTO$Update.class
    │  │  │          ProductDTO.class
    │  │  │          UsersDTO$Cart.class
    │  │  │          UsersDTO$Create.class
    │  │  │          UsersDTO$Delete.class
    │  │  │          UsersDTO$Get.class
    │  │  │          UsersDTO$LogIn.class
    │  │  │          UsersDTO$Order.class
    │  │  │          UsersDTO$Update.class
    │  │  │          UsersDTO.class
    │  │  │
    │  │  └─util
    │  │          DBUtil.class
    │  │
    │  ├─m2e-wtp
    │  │  └─web-resources
    │  │      └─META-INF
    │  │          │  MANIFEST.MF
    │  │          │
    │  │          └─maven
    │  │              └─playdata
    │  │                  └─step12_webProject
    │  │                          pom.properties
    │  │                          pom.xml
    │  │
    │  └─test-classes
    └─WebContent
        │  addProduct.jsp
        │  cart.jsp
        │  index.html
        │  login.jsp
        │  manageOrders.jsp
        │  manageProduct.jsp
        │  manageUsers.jsp
        │  myPage.jsp
        │  orders.jsp
        │  productCategory.jsp
        │  productName.jsp
        │  register.jsp
        │  shop.jsp
        │  showError.jsp
        │  updateUser.jsp
        │
        ├─common
        │      headerNav.jsp
        │      searchBar.jsp
        │
        ├─css
        │      style.css
        │
        ├─images
        │      1.jpg
        │      2.jpg
        │      3.jpg
        │      4.jpg
        │      5.jpg
        │      6.jpg
        │      7.jpg
        │
        ├─js
        │      canvas.js
        │
        ├─META-INF
        │      MANIFEST.MF
        │
        └─WEB-INF
            │
            └─lib
                    taglibs-standard-impl-1.2.5.jar
                    taglibs-standard-spec-1.2.5.jar
    ```

- java src 디렉토리 트리

    ```html
    C:.
    ├─controller
    │      Controller.java
    │
    ├─exception
    │      MessageException.java
    │      NotExistException.java
    │
    ├─META-INF
    │      persistence.xml
    │
    ├─model
    │  ├─DAO
    │  │      CartDAO.java
    │  │      OrdersDAO.java
    │  │      ProductDAO.java
    │  │      Service.java
    │  │      UsersDAO.java
    │  │
    │  ├─domain
    │  │      Cart.java
    │  │      Orders.java
    │  │      Product.java
    │  │      Users.java
    │  │
    │  └─DTO
    │          CartDTO.java
    │          OrdersDTO.java
    │          ProductDTO.java
    │          UsersDTO.java
    │
    └─util
            DBUtil.java
    ```

- WebContent 디렉토리 트리

    ```html
    C:.
    │  addProduct.jsp
    │  cart.jsp
    │  index.html
    │  login.jsp
    │  manageOrders.jsp
    │  manageProduct.jsp
    │  manageUsers.jsp
    │  myPage.jsp
    │  orders.jsp
    │  productCategory.jsp
    │  productName.jsp
    │  register.jsp
    │  shop.jsp
    │  showError.jsp
    │  updateUser.jsp
    │
    ├─common
    │      headerNav.jsp
    │      searchBar.jsp
    │
    ├─css
    │      style.css
    │
    ├─images
    │      1.jpg
    │      2.jpg
    │      3.jpg
    │      4.jpg
    │      5.jpg
    │      6.jpg
    │      7.jpg
    │
    ├─js
    │      canvas.js
    │
    ├─META-INF
    │      MANIFEST.MF
    │
    └─WEB-INF
        └─lib
                taglibs-standard-impl-1.2.5.jar
                taglibs-standard-spec-1.2.5.jar
    ```

## 🚀 트러블 슈팅 / 이슈
- **JSTL 조건문**
    - `JSP` 내부 `<c:if>` 태그 사용시 조건은 모두 EL Tag "${}" 내부에서만 작성

    ```html
    예시
    <c:if test="${product.idx == 1}">    O
    <c:if test="${product.idx} == 1">    X

    <c:if test="${not empty sessionScope.user && sessionScope.user.admin==0}">   O
    <c:if test="${not empty sessionScope.user} && ${sessionScope.user.admin==0}">   X
    ```

- **DELETE ON CASCADE**
    - 회원 정보 삭제 시 부모자식 관계로 mapping 된 테이블(orders, cart) 도 같이 삭제될 수 있게 `on delete cascade` 설정 필수
    - cascade 설정은 Entity, SQL중 하나만 적용해도 사용 가능

- **1:多 매핑 관계 내 객체 수정**
    - 수정할 객체가 기본 타입 속성인지, 참조 타입 속성인지 확인 필요
    - 매핑된 테이블은 DB상 참조객체의 PK만 가지고 있는것처럼 보이지만 사실은 연결된 참조객체의 모든 속성을 보유
    - 매핑된 테이블은 참조하는 엔티티의 PK활용 → 수정 시 PK로 DB로 접근하여 매핑 관계를 재정의

- `CSS`**,** `JS` **등 브라우저 언어 미적용**
    - 브라우저 언어가 업데이트 안될 시 브라우저 "캐시 비우기 및 강력새로고침" 실행

- **GitHub Auto-merge - 항상 의심!**
    - 수정 전 코드라인으로 Auto-merge 주의
    - 파일명 수정 시 merge 브랜치의 base 확인
    - Git Bash 관련 문제 발생


## 🚩 아쉬운 점 / 추가하고 싶은 점
- 장바구니(cart) 내 중복 사항에 대해서 수량으로 표현 / 수량 수정 기능 구현
- 주문내역 수를 기준으로 판매 best 물건 리스트 출력 구현
- 페이지 이동 방식에 따라 트랜젝션 성공 메세지를 출력하지 못한 것 아쉬움 → 팝업 창 구현
- 시간이 촉박해서 WebContent 부분 파일 경로를 정리하지 못한 것이 아쉬움
