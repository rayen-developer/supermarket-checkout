# 🛒 Supermarket Checkout System

## 📌 Overview

This project implements a simplified supermarket checkout system.

The system supports:

- Adding and Removing products to a cart
- Applying weekly multi-buy offers automatically
- Calculating the final price including discounts
- Exposing REST APIs for checkout operations

The focus of the implementation is **clean business logic, extensibility, and testability**.

---

## 🧱 Tech Stack

- **Java 17**
- **Spring Boot**
- **Maven**
- **Lombok**
- **JUnit 5**
- **Springdoc OpenAPI (Swagger UI)**

---

## 🏗 Architecture

The project follows a layered architecture:
- **controller**
- **service**
- **domain**


### Domain Layer
Contains the core business models:

- `Product`
- `Cart`
- `CartItem`
- `Offer` (interface)
- `MultiBuyOffer` (Offer implementation)

This abstraction allows easy extension for additional offer types such as: Buy One Get One Free
, Percentage-based discounts, etc.

### Service Layer
`CheckoutService` is responsible for:

- Calculating total price before discount
- Applying offers
- Returning final total price

### Controller Layer
Exposes REST endpoints for checkout operations.

---

## 💰 Offer Logic

Calculation steps:

1. Count eligible items
2. Determine how many bundles (how many times the offer will be applied)
3. Apply offer price for bundles
4. Add remaining items with normal price

**Example:**

| Product | Normal Price | Offer Price | Minimum Quantity |
|---------|--------------|-------------|-----------------|
| Apple   | 0.30€        | 0.45€       | 2               |

---

## 🚀 Running the Application

```bash
mvn clean install
mvn spring-boot:run
```` 
## 📖 API Documentation

Swagger UI is available at:

http://localhost:8080/swagger-ui/index.html

You can explore and test the API endpoints directly from the browser:

| HTTP Method | Endpoint              | Description                           |
|-------------|-----------------------|---------------------------------------|
| POST        | /api/cart/add         | Adds product to the cart              |
| GET         | /api/cart/totalPrice  | Calculates total price without offers |
| GET         | /api/cart/checkout | Calculates total with offers          |


## 🧪 Running Tests

Run the tests with:

```bash 
mvn test
```` 

Unit tests cover:

- Offer logic (MultiBuyOfferTest)

- Checkout calculation (CheckoutServiceTest)
