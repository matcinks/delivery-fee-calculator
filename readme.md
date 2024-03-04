# Simple backend application calculating delivery fee based on received requirements
## Description

This backend application, created in Kotlin, exposes an endpoint `/delivery_fee` for a POST request to calculate delivery fees based on specified conditions.

## Specification

- Rules for calculating a delivery fee:
    - If the cart value is less than 10€, a small order surcharge is added. The surcharge is the difference between the cart value and 10€.
    - A base delivery fee for the first 1000 meters is 2€, and additional charges apply for longer distances. Every additional 500 meters adds 1€. Even if the distance is shorter than 500 meters, the minimum fee is always 1€.
    - If the number of items is five or more there is an additional surcharge based on the number of items. An additional surcharge of 50 cents is added for each item above four. If there are more than 12 items in the cart an extra "bulk" fee of 1,2€ applies. Maximum fee of 15€.     
    - Friday rush (3 - 7 PM UTC) multiplies the total fee by 1.2x, capped at 15€.
    - The delivery fee can never be more than 15€, including possible surcharges.
    - Free delivery for cart values equal to or more than 200€.

## Example calculations

1. Delivery fee calculated mainly based on cart value
  - cart value: 9,99€
  - delivery distance: 499m
  - number of items: 4
  - order date: 29.02.2024 13:00 (not rush hours)
  - **total delivery: (10,00 - 9,99) + (1 * 1,00) = 1,01€**
2. Delivery fee calculated based on delivery distance
  - cart value: 10,00€
  - delivery distance: 1500m
  - number of items: 4
  - order date: 29.02.2024 13:00 (not rush hours)
  - **total delivery: (3 * 1,00) = 3,00€**
3. Delivery fee calculated based on number of items
  - cart value: 10,00€
  - delivery distance: 500m
  - number of items: 14
  - order date: 29.02.2024 13:00 (not rush hours)
  - **total delivery: (1 * 1,00) + (((14 - 4) * 0,50) + 1,20) = 7,20€**
4. Delivery fee calculated based on delivery date
  - cart value: 10,00€
  - delivery distance: 500m
  - number of items: 4
  - order date: 01.03.2024 15:00 (rush hours)
  - **total delivery: (1 * 1,00) * 1,20 = 1,20€**
5. Delivery fee calculated based on all values, cut to maximum fee
  - cart value: 1,00€
  - delivery distance: 3000m
  - number of items: 14
  - order date: 29.02.2024 15:00 (not rush hours)
  - **total delivery: (10,00 - 1,00) + (3 * 1,00) + (((14 - 4) * 0,50) + 1,20) = 18,20€ (above limit)**
  - **total delivery cut to: 15,00€**
6. Free delivery
  - cart value: 250,00€ (above limit)
  - delivery distance: 499m
  - number of items: 4
  - order date: 29.02.2024 13:00 (not rush hours)
  - **total delivery: free**


## Tests

The project includes several unit tests and integration tests. Unit tests cover all business logic.

## Usage

### Using Terminal:

Requirements: Java 17, Maven 3.9.6.
Navigate to project directory and run following commands:

```bash
mvn clean package
java -jar ./target/delivery-fee-calculator-1.0.0-SNAPSHOT.jar
```

### Using Docker:

Requirements: Docker v24.0.7.
Navigate to project directory and run following commands:

```bash
mvn clean package
docker build -t delivery-fee-calculator .
docker run delivery-fee-calculator -p 8080:8080
```

### Using IntelliJ:
Import the project into the IDE.
Mark additional directories:
`src/integration/java` as "Test Sources Root" and 
`src/integration/resources` as "Test Resources Root".
Run the project inside the IDE.

### Manual Testing
Use tools like Postman to send a request to `http://localhost:8080/delivery_fee` with JSON-formatted data.

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

### Assignment Source
This project is implemented based on a preliminary assignment for the Wolt Internship, source: [https://github.com/woltapp/engineering-internship-2024](https://github.com/woltapp/engineering-internship-2024)