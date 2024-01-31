# Preliminary Assignment for Wolt Summer 2024 Engineering Internship

## Description

This backend application, created in Kotlin, exposes an endpoint `/deliveryFee` for a POST request to calculate delivery fees based on specified conditions.

## Specification

- Rules for calculating a delivery fee:
    - If the cart value is less than 10€, a small order surcharge is added.
    - A base delivery fee for the first 1000 meters is 2€, and additional charges apply for longer distances.
    - Additional surcharges based on the number of items, with a maximum fee of 15€.
    - Free delivery for cart values equal to or more than 200€.
    - Friday rush (3 - 7 PM UTC) multiplies the total fee by 1.2x, capped at 15€.

## Tests

The project includes several unit tests and integration tests.

## Usage

### Using Terminal:

Requirements: Java 17, Maven 3.9.6.
Navigate to project directory and run following commands:

```bash
mvn clean package
java -jar ./target/deliver-fee-calculator-1.0.0-SNAPSHOT.jar
```

### Using Docker:

Requirements: Docker v24.0.7.
Navigate to project directory and run following commands:

```bash
docker build -t delivery-fee-calculator .
docker run delivery-fee-calculator
```

### Using IntelliJ:
Import the project into the IDE.
Mark additional directories:
`src/integration/java` as "Test Sources Root" and 
`src/integration/resources` as "Test Resources Root".
Run the project inside the IDE.

### Manual Testing
Use tools like Postman to send a request to `http://localhost:8080/deliveryFee` with JSON-formatted data.

Example request:
```json
{
  "cart_value": 15,
  "delivery_distance": 1200,
  "number_of_items": 8,
  "time": "2024-02-01T12:00:00Z"
}
```
Response:
```json
{
  "deliveryFee": 1385
}
```