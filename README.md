# linked-gold
<img src="https://github.com/user-attachments/assets/c6b17da3-e7f1-415a-8416-c593ff68d27d" alt="linked-gold" width="500"/>

<br>
<br>

Linked-Gold는 금 거래를 전문으로 하는 플랫폼으로,

사용자에게 금의 구매 및 판매를 지원하는 서비스를 제공합니다.

Linked-Gold는 효율적이고 확장 가능한 시스템을 구축하기 위해 두 개의 독립적인 서버를 구현하고 있습니다.

거래와 관련된 모든 기능을 담당하는 resource service, 인증과 관련된 기능을 처리하는 auth service로 이루어져있습니다.
<br>
<br>

### 목차

[1. 개발 환경](#1-개발-환경)

[2. 기능](#2-기능)

[3. 브랜치 전략](#3-브랜치-전략)

[4. Getting Started](#4-getting-started)

[5. 데이터베이스](#5-데이터베이스)

[6. 프로젝트 구조](#6-프로젝트-구조)

[7. 서비스 아키텍처](#7-서비스-아키텍처)

<br><br>

# 1. 개발 환경

- JAVA 21
- springboot 3.3.3
- gradle
- MariaDB
- Redis
- GRPC
- Resource Service
    - http://localhost:9999
    - http://localhost:9999/swagger-ui/index.html
- Auth Service
    - http://localhost:8888
<br><br>

# **2. 기능**

| 번호 | Method | URL | **Authorization** | Description |
| --- | --- | --- | --- | --- |
| 1 | POST | localhost:9999/apis/users |  | 사용자 회원가입 |
| 2 | POST | localhost:9999/apis/users/login |  | 사용자 로그인 및 토큰 발급 |
| 3 | POST | localhost:9999/apis/token/reissue | ✔️ | 리프레시 토큰으로 엑세스 토큰 재발급 |
| 4 | POST | localhost:9999/apis/invoices/{id} | ✔️ | 주문 생성 |
| 5 | GET | localhost:9999/apis/invoices/{id} | ✔️ | 주문 상세 조회 |
| 6 | PUT | localhost:9999/apis/invoices/payment | ✔️ | 주문 결제 처리 |
| 7 | PUT | localhost:9999/apis/invoices/shipment | ✔️ | 주문 배송 처리 |
| 8 | PUT | localhost:9999/apis/invoices/cancel | ✔️ | 주문 취소 |
| 9 | GET | localhost:9999/apis/invoices | ✔️ | 주문 목록 조회 |
<br>

# 3. 브랜치 전략

**Branch - Git Flow**

- main : 배포 단계
- develop : 개발 단계
- feat : 기능 단위
<br><br>

**Commit**

| Commit Type | Description |
| --- | --- |
| chore | 빌드 수정, Production Code 변경 없음 |
| feat | 새로운 기능 |
| refactor | 기능, 코드 개선 |
| docs | 문서 수정 |
| remove | 파일을 삭제 |
| comment | 주석 추가 및 변경 |
<br>

# **4. Getting Started**

**Create .env file**

- resource service
    
    ```jsx
    # 서버 관련 설정
    SERVER_PORT=
    
    # gRPC 설정
    GRPC_PORT=
    
    # 데이터베이스 연결 정보
    DB_URL=
    DB_USERNAME=
    DB_PASSWORD=
    
    # JWT 정보
    JWT_KEY=
    
    # REDIS
    REDIS_HOST=
    REDIS_PORT=
    
    ```
    
- auth service
    
    ```jsx
    # 서버 관련 설정
    SERVER_PORT=
    
    # gRPC 설정
    GRPC_PORT=
    
    # 데이터베이스 연결 정보
    DB_URL=
    DB_USERNAME=
    DB_PASSWORD=
    ```
<br>

**Setting Up Redis with Docker**
```
docker run --name redis -p 6379:6379 -d redis:latest
docker exec -it redis redis-cli
```
<br>

**Clone the Repository**

```
git clone https://github.com/nyximos/linked-gold
cd linked-gold
```

**Build the Project**

```
./gradlew build
```
<br>

**Run the Application**

`./gradlew bootRun`
<br>
<br>

# **5. 데이터베이스**

[DB Diagram](https://dbdiagram.io/d/linkd-gold-66e1a0646dde7f4149c789f7)
<br>

<img width="817" alt="image" src="https://github.com/user-attachments/assets/e6fd0ed5-5ef6-4709-a98f-32ae2f4426a9">
<br>

# 6. 프로젝트 구조
```jsx
├── README.md
├── auth-service
│   ├── HELP.md
│   ├── build.gradle
│   ├── gradle
│   │   └── wrapper
│   │       ├── gradle-wrapper.jar
│   │       └── gradle-wrapper.properties
│   ├── gradlew
│   ├── gradlew.bat
│   ├── settings.gradle
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── gold
│       │   │           ├── AuthServiceApplication.java
│       │   │           ├── auth
│       │   │           │   ├── converter
│       │   │           │   │   └── RefreshTokenConverter.java
│       │   │           │   ├── persistence
│       │   │           │   │   └── repository
│       │   │           │   │       ├── RefreshTokenRepository.java
│       │   │           │   │       └── entity
│       │   │           │   │           └── RefreshTokenEntity.java
│       │   │           │   └── service
│       │   │           │       ├── AuthService.java
│       │   │           │       ├── TokenProvider.java
│       │   │           │       └── validator
│       │   │           │           ├── RefreshTokenValidator.java
│       │   │           │           └── TokenValidator.java
│       │   │           └── core
│       │   │               ├── config
│       │   │               │   ├── DefaultRedisRepository.java
│       │   │               │   └── SwaggerConfig.java
│       │   │               ├── exception
│       │   │               │   ├── ErrorCode.java
│       │   │               │   ├── GoldException.java
│       │   │               │   ├── InvalidRefreshTokenException.java
│       │   │               │   └── InvalidTokenException.java
│       │   │               └── util
│       │   │                   └── TokenUtils.java
│       │   ├── proto
│       │   │   └── auth.proto
│       │   └── resources
│       │       └── application.yml
│       └── test
│           └── java
│               └── com
│                   └── gold
│                       └── AuthServiceApplicationTests.java
└── resource-service
    ├── HELP.md
    ├── build.gradle
    ├── gradle
    │   └── wrapper
    │       ├── gradle-wrapper.jar
    │       └── gradle-wrapper.properties
    ├── gradlew
    ├── gradlew.bat
    ├── settings.gradle
    └── src
        ├── main
        │   ├── java
        │   │   └── com
        │   │       └── gold
        │   │           ├── ResourceServiceApplication.java
        │   │           ├── client
        │   │           │   ├── AuthClient.java
        │   │           │   └── AuthHandler.java
        │   │           ├── core
        │   │           │   ├── code
        │   │           │   │   ├── GoldType.java
        │   │           │   │   ├── InvoiceSearchType.java
        │   │           │   │   ├── InvoiceType.java
        │   │           │   │   └── OrderStatus.java
        │   │           │   ├── config
        │   │           │   │   ├── DefaultJpaRepository.java
        │   │           │   │   ├── JpaConfig.java
        │   │           │   │   ├── PasswordEncoderConfig.java
        │   │           │   │   ├── QueryDslConfig.java
        │   │           │   │   └── SwaggerConfig.java
        │   │           │   ├── constant
        │   │           │   │   ├── DateConst.java
        │   │           │   │   └── ResourceConstants.java
        │   │           │   ├── exception
        │   │           │   │   ├── AlreadyShippedOrderException.java
        │   │           │   │   ├── EmailAlreadyInUseException.java
        │   │           │   │   ├── ErrorCode.java
        │   │           │   │   ├── GoldException.java
        │   │           │   │   ├── GoldNotFoundException.java
        │   │           │   │   ├── InsufficientWeightException.java
        │   │           │   │   ├── InvalidPasswordException.java
        │   │           │   │   ├── InvalidRefreshTokenException.java
        │   │           │   │   ├── InvalidTokenException.java
        │   │           │   │   ├── InvoiceNotFoundException.java
        │   │           │   │   ├── OrderCanceledException.java
        │   │           │   │   ├── PaymentFailedException.java
        │   │           │   │   ├── UnknownInvoiceTypeException.java
        │   │           │   │   ├── UnpaidOrderException.java
        │   │           │   │   └── UserNotFoundException.java
        │   │           │   ├── filter
        │   │           │   │   └── TokenValidationFilter.java
        │   │           │   ├── handler
        │   │           │   │   ├── GlobalExceptionHandler.java
        │   │           │   │   └── RequestHandler.java
        │   │           │   ├── util
        │   │           │   │   ├── InvoiceIdGenerator.java
        │   │           │   │   └── PaginationUtil.java
        │   │           │   └── wrapper
        │   │           │       ├── LinksResponse.java
        │   │           │       ├── PageResponse.java
        │   │           │       ├── ResultResponse.java
        │   │           │       └── TokenUser.java
        │   │           └── resource
        │   │               ├── controller
        │   │               │   ├── api
        │   │               │   │   ├── InvoiceController.java
        │   │               │   │   ├── TokenController.java
        │   │               │   │   └── UserController.java
        │   │               │   └── model
        │   │               │       ├── request
        │   │               │       │   ├── InvoiceSearchRequest.java
        │   │               │       │   ├── LoginRequestModel.java
        │   │               │       │   └── SignUpRequestModel.java
        │   │               │       └── response
        │   │               │           ├── InvoiceListResponse.java
        │   │               │           ├── InvoiceResponse.java
        │   │               │           └── TokenModel.java
        │   │               ├── converter
        │   │               │   ├── GoldConverter.java
        │   │               │   ├── InvoiceConverter.java
        │   │               │   ├── TokenConverter.java
        │   │               │   └── UserConverter.java
        │   │               ├── persistence
        │   │               │   └── repository
        │   │               │       ├── GoldRepository.java
        │   │               │       ├── InvoiceRepository.java
        │   │               │       ├── InvoiceRepositoryCustom.java
        │   │               │       ├── InvoiceRepositoryImpl.java
        │   │               │       ├── UserRepository.java
        │   │               │       └── entity
        │   │               │           ├── BaseEntity.java
        │   │               │           ├── GoldEntity.java
        │   │               │           ├── InvoiceEntity.java
        │   │               │           └── UserEntity.java
        │   │               └── service
        │   │                   ├── GoldService.java
        │   │                   ├── InvoiceService.java
        │   │                   ├── TokenService.java
        │   │                   ├── UserService.java
        │   │                   ├── delegator
        │   │                   │   ├── LoginValidateDelegator.java
        │   │                   │   └── validate
        │   │                   │       ├── CancelValidator.java
        │   │                   │       ├── DuplicateEmailValidator.java
        │   │                   │       ├── EmailValidator.java
        │   │                   │       ├── GoldWeightValidator.java
        │   │                   │       ├── LoginValidator.java
        │   │                   │       ├── PasswordValidator.java
        │   │                   │       ├── PaymentValidator.java
        │   │                   │       ├── ShipmentValidator.java
        │   │                   │       └── UserValidator.java
        │   │                   └── domain
        │   │                       └── Gold.java
        │   ├── proto
        │   │   └── auth.proto
        │   └── resources
        │       └── application.yml
        └── test
            └── java
                └── com
                    └── gold
                        └── ResourceServiceApplicationTests.java
```

# 7. 서비스 아키텍처
<img width="909" alt="아키텍처" src="https://github.com/user-attachments/assets/d1105558-77ab-4f85-a8fb-ee2fa9c24d70">
