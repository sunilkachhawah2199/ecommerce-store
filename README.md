# E-commerce Store Backend API

This project is a scalable backend API for an e-commerce store, developed using Java and Spring Boot. It includes services for managing products and shopping carts, optimized with Redis caching for improved performance and faster response times. The API follows RESTful principles and is designed with a focus on maintainability, scalability, and security using industry best practices such as SOLID and Object-Oriented Programming (OOP) principles.
## documentation

```bash
  http://localhost:8080/swagger-ui/index.html
```
## Key Features

### Product Management Service
- Allows CRUD operations on products, enabling the creation, updating, and deletion of product details.
- Supports advanced querying capabilities such as listing products by category and retrieving all available product categories.
- Provides endpoints to handle bulk product additions, which is useful for managing large inventories efficiently.

### Cart Management Service
- Facilitates the creation and management of shopping carts for users.
- Supports adding items to the cart, updating quantities, and removing items as needed.
- Provides functionality for viewing cart contents, either for a specific user or across all users, enhancing the shopping experience.

### Performance Optimization with Redis
- Integrated Redis caching to reduce database load and optimize response times, significantly improving performance from 30ms to 2ms.
- The caching layer helps to store frequently accessed data, such as product details and cart information, which minimizes the number of direct database calls.

### Security and Authentication
- The API is secured using JWT for authentication and Spring Security for authorization, ensuring that all endpoints are protected and accessible only to authenticated users with the appropriate permissions.

### Scalable Architecture
- Built on a robust and scalable architecture using Spring Boot and the MVC pattern, allowing for easy maintenance and future enhancements.
- Utilizes best practices in software development, including SOLID principles, to ensure clean, manageable, and testable code.

### Build and Dependency Management
- Utilized Maven for managing project dependencies and build automation, ensuring smooth integration and continuous delivery processes.

## API Endpoints

### Product Controller
- **GET /api/products/{id}**: Retrieve a specific product by its ID.
- **PUT /api/products/{id}**: Update an existing product by its ID.
- **DELETE /api/products/{id}**: Delete a product by its ID.
- **POST /api/products/list**: Add a list of products in bulk.
- **GET /api/products/**: Retrieve all products.
- **POST /api/products/**: Add a new product.
- **GET /api/products/category/{category}**: Retrieve products by category.
- **GET /api/products/categories**: Retrieve a list of all product categories.

### Cart Controller
- **PUT /api/cart/{cart_id}/update/{item_id}**: Update the quantity of a specific item in the cart.
- **POST /api/cart/{user_id}**: Create a new cart for a user.
- **POST /api/cart/{cart_id}/add**: Add items to the cart by cart ID.
- **GET /api/cart/{cart_id}**: Retrieve a specific cart by cart ID.
- **GET /api/cart/**: Retrieve all carts.
- **DELETE /api/cart/{cart_id}/remove/{item_id}**: Remove a specific item from the cart.

## Technologies Used
- **Java 11**: The programming language used for developing the backend.
- **Spring Boot**: Framework for building the API, providing ease of setup, development, and deployment.
- **Spring Data JPA**: For database interaction and data persistence.
- **Spring Security and JWT**: For authentication and securing API endpoints.
- **Redis**: Used as a caching layer to optimize performance and reduce database calls.
- **Maven**: For project build and dependency management.
- **MongoDB or SQL Database**: For storing product and cart data.

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/ecommerce-backend.git
   ```
2. Navigate to the project directory:
   ```bash
    cd ecommerce-backend
    ```
3. Run the application using Maven:
    ```bash
    mvn spring-boot:run
    ```
4. Access the API documentation at:
    ```bash
     http://localhost:8080/swagger-ui/index.html
   ```
