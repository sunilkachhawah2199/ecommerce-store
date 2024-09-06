
# Ecommerce Store

The E-commerce Backend System is a robust and scalable backend solution designed to support the core functionalities of an online shopping platform. This system provides a comprehensive API that handles user authentication, product management, and secure access to resources, making it ideal for e-commerce applications.



## Features
1. User Authentication and Authorization:
- Users can sign up and log in using their credentials.
- The system employs JWT (JSON Web Token) for secure authentication, ensuring that only authorized users can access protected resources.
- JWT tokens are issued upon successful login and are used for validating user sessions across various microservices.

2. Product Management:

- Add Product: Authenticated users can add new products to the catalog with details such as name, description, price, and availability.
- Update Product: Users can update existing product details, allowing for dynamic changes in the product offerings.
- Delete Product: The system allows authorized users to remove products from the catalog, maintaining the integrity and relevancy of the inventory.
- View Products: Users can retrieve a list of products with full details, making it easy to browse through the available items.

3. Security Implementation:

- Security is a core component of the system, with JWT tokens providing a robust layer of authentication and authorization.
- JWT-based security ensures that all transactions and data exchanges between clients and microservices are secure and tamper-proof.
## Technology Stack
- Spring Boot: For building robust and scalable microservices.
- Spring Security: To implement security features such as JWT-based authentication.
- Java: As the core programming language for business logic implementation.
- MySQL Database: For persistent storage of user and product data.
- RESTful APIs: For communication between clients and services, supporting CRUD operations for product management.