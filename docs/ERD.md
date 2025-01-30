# ERD Diagram

### 연관관계 설명
* USERS와 USER_COUPON: 1:N 관계 (사용자 1명이 여러 개의 쿠폰을 가질 수 있음)
* USERS와 USER_CART: 1:N 관계 (사용자 1명이 여러 개의 장바구니 상품을 가질 수 있음)
* USERS와 ORDERS: 1:N 관계 (사용자 1명이 여러 개의 주문을 가질 수 있음)
* USERS와 USER_COUPON: 1:N 관계 (사용자 1명이 여러 개의 쿠폰을 가질 수 있음)
* BRAND와 PRODUCT: 1:N 관계 (브랜드 1개가 여러 개의 상품을 가질 수 있음)
* PRODUCT와 PRODUCT_STOCK: 1:N 관계 (상품 1개에 여러 재고가 연결될 수 있음, SKU로 구분됨)
* PRODUCT_STOCK와 CART: 1:N 관계 (재고 1개가 여러 장바구니 항목에 포함될 수 있음)
* PRODUCT_STOCK와 ORDER_ITEM: 1:N 관계 (재고 1개가 여러 주문 항목으로 연결될 수 있음)
* COUPON와 USER_COUPON: 1:N 관계 (한 가지의 쿠폰을 여러 사용자가 가질 수 있음)
* COUPON와 ORDER_COUPON: 1:N 관계 (한 가지의 쿠폰이 여러 주문에 적용될 수 있음)
* ORDERS와 ORDER_ITEM: 1:N 관계 (주문 1개가 여러 주문 항목을 포함할 수 있음)
* ORDERS와 ORDER_COUPON: 1:1 관계 (주문 1개에 쿠폰 1개)
* ORDERS와 PAYMENT: 1:1 관계 (주문 1개에 결제 1개)

```mermaid
erDiagram
    USERS {
        BIGINT id PK
        VARCHAR name "사용자이름"
        INT point "보유포인트"
        DATETIME created_at "생성일시"
        DATETIME updated_at "수정일시"
        DATETIME deleted_at "삭제일시"
    }
    
    USER_COUPON {
        BIGINT id PK
        BIGINT user_id FK
        BIGINT coupon_id FK
        VARCHAR coupon_name "쿠폰이름"
        BIGINT quantity "보유수량"
        DATETIME used_at "사용일시"
        DATETIME expired_at "만료일시"
        DATETIME created_at "생성일시"
        DATETIME updated_at "수정일시"
        DATETIME deleted_at "삭제일시"
    }
   
    USER_CART {
        BIGINT id PK
        BIGINT user_id FK
        BIGINT stock_id FK
        VARCHAR sku "상품코드"
        INT quantity "상품수량"
        DECIMAL total_price "상품별 합계금액"
        DATETIME created_at "생성일시"
        DATETIME updated_at "수정일시"
        DATETIME deleted_at "삭제일시"
    }

    BRAND {
        BIGINT id PK
        VARCHAR name "브랜드명"
        DATETIME created_at "생성일시"
        DATETIME updated_at "수정일시"
        DATETIME deleted_at "삭제일시"
    }

    PRODUCT {
        BIGINT id PK
        BIGINT brand_id FK
        VARCHAR name "상품명"
        DATETIME created_at "생성일시"
        DATETIME updated_at "수정일시"
        DATETIME deleted_at "삭제일시"
    }

    PRODUCT_STOCK {
        BIGINT id PK
        BIGINT product_id FK
        VARCHAR sku "상품코드"
        VARCHAR name "명칭"
        BIGINT quantity "수량"
        DECIMAL price "가격"
        DATETIME created_at "생성일시"
        DATETIME updated_at "수정일시"
        DATETIME deleted_at "삭제일시"
    }

    COUPON {
        BIGINT id PK
        VARCHAR coupon_code "쿠폰코드"
        VARCHAR coupon_name "쿠폰이름"
        VARCHAR discount_type "할인 타입(PERCENTAGE,AMOUNT)"
        VARCHAR discount_target "할인 대상(PRODUCT,ORDER)"
        INT quantity "발급수량"
        DECIMAL discount_amount "할인가격"
        DECIMAL discount_rate "할인률"
        DECIMAL min_order_amount "최소주문금액"
        DECIMAL max_discount_amount "최대할인금액"
        DATETIME created_at "생성일시"
        DATETIME updated_at "수정일시"
        DATETIME deleted_at "삭제일시"
    }
    
    ORDERS {
        BIGINT id PK
        BIGINT user_id FK
        DECIMAL price "주문금액"
        VARCHAR status "상태(주문요청,주문완료,주문취소)"
        DATETIME created_at "생성일시"
        DATETIME updated_at "수정일시"
        DATETIME deleted_at "삭제일시"
    }

    ORDER_ITEM {
        BIGINT id PK
        BIGINT order_id FK
        BIGINT stock_id FK
        INT quantity "상품수량"
        INT total_price "총 금액"
        DATETIME created_at "생성일시"
        DATETIME updated_at "수정일시"
        DATETIME deleted_at "삭제일시"
    }

    ORDER_COUPON {
        BIGINT id PK
        BIGINT order_id FK
        BIGINT user_coupon_id FK
        DATETIME created_at "생성일시"
        DATETIME updated_at "수정일시"
        DATETIME deleted_at "삭제일시"
    }

    PAYMENT {
        BIGINT id PK 
        BIGINT user_id FK  
        BIGINT order_id FK
        DECIMAL amount "최종결제금액"
        VARCHAR method "결제방식"
        VARCHAR status "타입(결제완료,결제취소)"
        DATETIME created_at "생성일시"
        DATETIME updated_at "수정일시"
        DATETIME deleted_at "삭제일시"
    }

    %% Relationships
    USERS ||--o{ USER_POINT : ""
    USERS ||--o{ USER_CART : ""
    USERS ||--o{ USER_COUPON : ""
    USERS ||--o{ ORDERS : ""
    BRAND ||--o{ PRODUCT : ""
    PRODUCT ||--o{ PRODUCT_STOCK : ""
    PRODUCT_STOCK ||--o{ USER_CART : ""
    PRODUCT_STOCK ||--o{ ORDER_ITEM : ""
    COUPON ||--o{ USER_COUPON : ""
    COUPON ||--o{ ORDER_COUPON : ""
    ORDERS ||--o{ ORDER_ITEM : ""
    ORDERS ||--|| ORDER_COUPON : ""
    ORDERS ||--|| PAYMENT : ""
```
