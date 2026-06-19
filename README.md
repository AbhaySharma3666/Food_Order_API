# 🍔 Foodies API

A Spring Boot REST API powering the **Foodies** online food delivery platform. It provides endpoints for user authentication, food catalog management, shopping cart operations, and order processing with Razorpay payment integration.

---

## 📋 Table of Contents

- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Getting Started](#-getting-started)
- [Environment Variables](#-environment-variables)
- [API Endpoints](#-api-endpoints)
- [Authentication](#-authentication)
- [Error Handling](#-error-handling)
- [Docker](#-docker)
- [License](#-license)

---

## 🛠 Tech Stack

| Technology             | Purpose                        |
| ---------------------- | ------------------------------ |
| **Java 21**            | Language runtime               |
| **Spring Boot 4.0.3**  | Application framework          |
| **Spring Security**    | Authentication & authorization |
| **Spring Data MongoDB**| Database access (MongoDB)      |
| **JWT (jjwt 0.12.6)**  | Stateless token authentication |
| **AWS SDK v2 (S3)**    | Food image storage             |
| **Razorpay Java SDK**  | Payment gateway integration    |
| **Lombok**             | Boilerplate reduction          |
| **Jackson**            | JSON serialization             |
| **Maven**              | Build & dependency management  |
| **Docker**             | Containerization               |

---

## 🏗 Architecture

The application follows a **layered architecture** with clear separation of concerns:

```
Controller → Service → Repository → MongoDB
     ↕            ↕
   DTOs        Entities
```

- **Controller** — Handles HTTP requests and route mapping
- **Service** — Business logic and orchestration
- **Repository** — Data access via Spring Data MongoDB
- **DTO** — Request/Response objects for API contracts
- **Entity** — MongoDB document models
- **Security** — JWT filter and authentication facade
- **Config** — Security, AWS, and Razorpay configuration
- **Exception** — Global exception handler with structured error responses

---

## 📁 Project Structure

```
foodiesapi/
├── src/main/java/com/abhayproj/
│   ├── FoodiesapiApplication.java      # Application entry point
│   ├── config/
│   │   ├── AWSConfig.java              # AWS S3 client configuration
│   │   ├── RazorpayConfig.java         # Razorpay client configuration
│   │   └── SecurityConfig.java         # Spring Security & CORS setup
│   ├── controller/
│   │   ├── AuthController.java         # Login endpoint
│   │   ├── CartController.java         # Cart CRUD operations
│   │   ├── FoodController.java         # Food catalog management
│   │   ├── OrderController.java        # Order & payment processing
│   │   └── UserController.java         # User registration
│   ├── dto/
│   │   ├── AuthenticationRequest.java  # Login request body
│   │   ├── AuthenticationResponse.java # Login response (JWT token)
│   │   ├── CartRequest.java            # Add/remove cart item
│   │   ├── CartResponse.java           # Cart state response
│   │   ├── ErrorResponse.java          # Standardized error body
│   │   ├── FoodRequest.java            # Create food item
│   │   ├── FoodResponse.java           # Food item response
│   │   ├── OrderItem.java              # Individual order line item
│   │   ├── OrderRequest.java           # Create order request
│   │   ├── OrderResponse.java          # Order details response
│   │   ├── UserRequest.java            # Registration request
│   │   └── UserResponse.java           # User info response
│   ├── entity/
│   │   ├── CartEntity.java             # MongoDB cart document
│   │   ├── FoodEntity.java             # MongoDB food document
│   │   ├── OrderEntity.java            # MongoDB order document
│   │   └── UserEntity.java             # MongoDB user document
│   ├── exception/
│   │   ├── GlobalExceptionHandler.java # Centralized error handling
│   │   └── ResourceNotFoundException.java
│   ├── repository/
│   │   ├── CartRepository.java
│   │   ├── FoodRepository.java
│   │   ├── OrderRepository.java
│   │   └── UserRepository.java
│   ├── security/
│   │   ├── AuthenticationFacade.java   # Interface for auth context
│   │   ├── AuthenticationFacadeImpl.java
│   │   └── JwtAuthenticationFilter.java # JWT token filter
│   ├── service/
│   │   ├── AppUserDetailsService.java  # UserDetailsService impl
│   │   ├── CartService.java            # Cart service interface
│   │   ├── CartServiceImpl.java
│   │   ├── FoodService.java            # Food service interface
│   │   ├── FoodServiceImpl.java
│   │   ├── OrderService.java           # Order service interface
│   │   ├── OrderServiceImpl.java
│   │   ├── UserService.java            # User service interface
│   │   └── UserServiceImpl.java
│   └── util/
│       └── JwtUtil.java                # JWT token generation/validation
├── src/main/resources/
│   └── application.yaml                # Application configuration
├── .env.example                        # Environment variable template
├── Dockerfile                          # Multi-stage Docker build
├── pom.xml                             # Maven build configuration
└── mvnw / mvnw.cmd                     # Maven wrapper scripts
```

---

## ✅ Prerequisites

- **Java 21** or later
- **Maven 3.9+** (or use the included Maven wrapper)
- **MongoDB** instance (local or [MongoDB Atlas](https://www.mongodb.com/atlas))
- **AWS S3 Bucket** for food image storage
- **Razorpay Account** for payment processing ([Dashboard](https://dashboard.razorpay.com/))

---

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone <repository-url>
cd foodiesapi
```

### 2. Configure environment variables

Copy the example environment file and fill in your credentials:

```bash
cp .env.example .env
```

Edit `.env` with your actual values (see [Environment Variables](#-environment-variables)).

### 3. Run the application

**Using Maven Wrapper:**

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

**Using Maven directly:**

```bash
mvn spring-boot:run
```

The API will start at `http://localhost:8080`.

### 4. Verify

```bash
curl http://localhost:8080/api/foods
```

---

## 🔐 Environment Variables

Create a `.env` file in the project root with the following variables:

| Variable               | Description                         | Example                          |
| ---------------------- | ----------------------------------- | -------------------------------- |
| `MONGODB_URI`          | MongoDB connection string           | `mongodb+srv://user:pass@cluster.mongodb.net/db` |
| `AWS_ACCESS_KEY_ID`    | AWS IAM access key                  | `AKIA...`                        |
| `AWS_SECRET_ACCESS_KEY`| AWS IAM secret key                  | `xxcZ...`                        |
| `AWS_REGION_NAME`      | AWS region for S3                   | `ap-south-1`                     |
| `S3_BUCKET_NAME`       | S3 bucket for food images           | `foodies-food-images`            |
| `JWT_SECRET_KEY`       | Secret key for JWT signing (≥32 chars) | `your_secure_random_string`   |
| `RAZORPAY_KEY`         | Razorpay API key                    | `rzp_test_...`                   |
| `RAZORPAY_SECRET`      | Razorpay API secret                 | `oSQo...`                        |
| `PORT`                 | Server port (default: `8080`)       | `8080`                           |

> [!CAUTION]
> Never commit the `.env` file to version control. It is already included in `.gitignore`.

---

## 📡 API Endpoints

### Authentication

| Method | Endpoint         | Description            | Auth Required |
| ------ | ---------------- | ---------------------- | :-----------: |
| POST   | `/api/register`  | Register a new user    | ❌            |
| POST   | `/api/login`     | Login & get JWT token  | ❌            |

### Food Catalog

| Method | Endpoint          | Description              | Auth Required |
| ------ | ----------------- | ------------------------ | :-----------: |
| GET    | `/api/foods`      | List all food items      | ❌            |
| GET    | `/api/foods/{id}` | Get a food item by ID    | ❌            |
| POST   | `/api/foods`      | Add a new food item      | ❌            |
| DELETE | `/api/foods/{id}` | Delete a food item       | ❌            |

> **Note:** The `POST /api/foods` endpoint accepts `multipart/form-data` with a `food` JSON part and a `file` image part.

### Shopping Cart

| Method | Endpoint          | Description                  | Auth Required |
| ------ | ----------------- | ---------------------------- | :-----------: |
| GET    | `/api/cart`       | Get the current user's cart  | ✅            |
| POST   | `/api/cart`       | Add an item to the cart      | ✅            |
| POST   | `/api/cart/remove`| Remove an item from the cart | ✅            |
| DELETE | `/api/cart`       | Clear the entire cart        | ✅            |

### Orders

| Method | Endpoint                     | Description                   | Auth Required |
| ------ | ---------------------------- | ----------------------------- | :-----------: |
| POST   | `/api/orders/create`         | Create an order with payment  | ✅            |
| POST   | `/api/orders/verify`         | Verify Razorpay payment       | ❌            |
| GET    | `/api/orders`                | Get current user's orders     | ✅            |
| GET    | `/api/orders/all`            | Get all orders (admin)        | ❌            |
| PATCH  | `/api/orders/status/{orderId}` | Update order status         | ❌            |
| DELETE | `/api/orders/{orderId}`      | Delete an order               | ❌            |

---

## 🔑 Authentication

The API uses **JWT (JSON Web Tokens)** for stateless authentication.

### Flow

1. **Register** — `POST /api/register` with name, email, and password.
2. **Login** — `POST /api/login` with email and password → receives a JWT token.
3. **Access protected routes** — Include the token in the `Authorization` header:

```
Authorization: Bearer <your_jwt_token>
```

### Example: Login

**Request:**
```json
POST /api/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "your_password"
}
```

**Response:**
```json
{
  "email": "user@example.com",
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

## ⚠️ Error Handling

The API uses a global exception handler that returns consistent error responses:

```json
{
  "status": 404,
  "message": "Food item not found with id: abc123",
  "timestamp": "2026-06-19T14:30:00",
  "errors": null
}
```

### Handled Exceptions

| Exception                         | HTTP Status | Description                      |
| --------------------------------- | :---------: | -------------------------------- |
| `ResourceNotFoundException`       | 404         | Requested resource not found     |
| `MethodArgumentNotValidException` | 400         | Request body validation failed   |
| `BadCredentialsException`         | 401         | Invalid email or password        |
| `UsernameNotFoundException`       | 404         | User not found during auth       |
| `ResponseStatusException`         | Varies      | Programmatic status exceptions   |
| `Exception` (catch-all)           | 500         | Unexpected server error          |

Validation errors include a field-level `errors` map:

```json
{
  "status": 400,
  "message": "Validation failed",
  "timestamp": "2026-06-19T14:30:00",
  "errors": {
    "email": "Email is required",
    "password": "Password must be at least 6 characters"
  }
}
```

---

## 🐳 Docker

### Build and run with Docker

```bash
# Build the image
docker build -t foodiesapi .

# Run the container
docker run -p 8080:8080 --env-file .env foodiesapi
```

The Dockerfile uses a **multi-stage build**:
1. **Build stage** — Uses `maven:3.9.5-eclipse-temurin-21` to compile and package the JAR.
2. **Runtime stage** — Uses `eclipse-temurin:21-jre` for a lightweight production image.

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📄 License

This project is for educational and personal use.
