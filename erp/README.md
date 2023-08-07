# ERP Project

## Description

This is an ERP (Enterprise Resource Planning) system that aims to manage various business processes and data for a company. The system provides functionalities related to customers, orders, products, and bills, allowing the company to efficiently handle its operations.

## Technologies Used

- Java
- Spring Framework (Spring Boot, Spring Data JPA, Spring Web)
- Jakarta Persistence (JPA)
- PostgreSQL (Database)
- Postman (API testing)
- Lombok (for auto-generating getters, setters, and constructors)
- Maven (Build and Dependency Management)
- Git (Version Control)

## Features

- Customer Management: CRUD operations for managing customer information.
- Order Management: Create, update, and view orders along with their details.
- Product Management: Manage the product inventory and information.
- Bill Generation: Generate bills for completed orders with the total amount.

## Setup

1. Install Java Development Kit (JDK) version 8 or above.
2. Install MySQL database and create a new database for the project.
3. Clone this repository to your local machine using Git.
4. Open the project in your preferred IDE (e.g., IntelliJ, Eclipse).
5. Configure the database connection in `application.properties` with your MySQL credentials.
6. Run the application using Maven: `mvn spring-boot:run`.
7. The application should now be accessible at `http://localhost:8080`.

## API Endpoints

- **Customer Management:**
  - `GET /api/customers`: Get a list of all customers.
  - `GET /api/customers/{customerId}`: Get customer details by ID.
  - `POST /api/customers`: Create a new customer.
  - `PUT /api/customers/{customerId}`: Update customer information.
  - `DELETE /api/customers/{customerId}`: Delete a customer by ID.

- **Order Management:**
  - `GET /api/orders`: Get a list of all orders.
  - `GET /api/orders/{orderId}`: Get order details by ID.
  - `POST /api/orders`: Create a new order.
  - `PUT /api/orders/{orderId}`: Update order information.
  - `DELETE /api/orders/{orderId}`: Delete an order by ID.

- **Product Management:**
  - `GET /api/products`: Get a list of all products.
  - `GET /api/products/{productId}`: Get product details by ID.
  - `POST /api/products`: Create a new product.
  - `PUT /api/products/{productId}`: Update product information.
  - `DELETE /api/products/{productId}`: Delete a product by ID.

- **Bill Generation:**
  - `POST /api/bills`: Generate a bill for a completed order.

## Contributions

Contributions to this project are welcome! If you find any issues or have suggestions for improvements, feel free to open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

