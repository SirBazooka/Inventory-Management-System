# Inventory Management System

A comprehensive Spring Boot application for managing product inventory and suppliers with a modern web interface and RESTful API.

## Technologies Used

- **Java 21+** - Core programming language
- **Spring Boot 6.x** - Application framework
- **Spring Web** - RESTful web services
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Data persistence layer
- **Thymeleaf** - Server-side template engine for web UI
- **H2 Database** - In-memory database for development
- **Maven** - Dependency management and build tool
- **Bean Validation** - Input validation
- **Spring Scheduling** - Automated tasks

## Architecture & Features

### Core Features
- **Product Management**: CRUD operations for products with stock tracking
- **Supplier Management**: Complete supplier information management
- **Low Stock Alerts**: Automated monitoring and notifications
- **User Authentication**: Role-based access control (ADMIN/STAFF)
- **Web Dashboard**: Real-time inventory overview
- **RESTful API**: Complete API for external integrations

### Security Implementation
- **Role-based Access Control**:
  - `ADMIN`: Full CRUD access to all resources
  - `STAFF`: Read access to products and suppliers
- **HTTP Basic Authentication**
- **Method-level Security**: Different permissions for different HTTP methods
- **CSRF Protection**: Configured for API usage

### Data Models
- **Product**: Name, quantity, price, low stock threshold, supplier relationship
- **Supplier**: Name, email, phone contact information
- **Many-to-One Relationship**: Products linked to suppliers

## 🛠️ Setup & Installation

### Prerequisites
- Java 21 or higher
- Maven 3.9+
- Any IDE (IntelliJ IDEA, Eclipse, VS Code)

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone <your-repository-url>
   cd InventoryManagementSystem
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - Web Interface: `http://localhost:8080`
   - H2 Database Console: `http://localhost:8080/h2-console`
   - API Base URL: `http://localhost:8080/product` or `http://localhost:8080/supplier`

## Usage Guide

### Web Interface

#### Dashboard (`/`)
- Overview of total products and suppliers
- Low stock alerts with highlighted items
- Recent products display
- Navigation to detailed views

#### Products Page (`/products`)
- Complete product listing
- Visual indicators for low stock items
- Product details including supplier information

#### Suppliers Page (`/suppliers`)
- Complete supplier directory
- Contact information display

### REST API Endpoints

#### Authentication
Use HTTP Basic Authentication with these credentials:
- **Admin User**: `username: admin, password: admin`
- **Staff User**: `username: staff, password: staff`

#### Product Endpoints
```http
GET    /product              # Get all products
GET    /product/{id}         # Get product by ID
POST   /product              # Create new product (ADMIN only)
PUT    /product/{id}         # Update product (ADMIN only)
DELETE /product/{id}         # Delete product (ADMIN only)
GET    /product/low-stock    # Get low stock products
```

#### Supplier Endpoints
```http
GET    /supplier             # Get all suppliers
GET    /supplier/{id}        # Get supplier by ID
POST   /supplier             # Create new supplier (ADMIN only)
PUT    /supplier/{id}        # Update supplier (ADMIN only)
DELETE /supplier/{id}        # Delete supplier (ADMIN only)
```

### Sample API Requests

#### Create a New Product (Admin Required)
```json
POST /product
Content-Type: application/json
Authorization: Basic YWRtaW46YWRtaW4=

{
    "name": "Laptop Computer",
    "quantity": 50,
    "price": 999.99,
    "lowStockThreshold": 5,
    "supplier": {
        "id": 1
    }
}
```

#### Create a New Supplier (Admin Required)
```json
POST /supplier
Content-Type: application/json
Authorization: Basic YWRtaW46YWRtaW4=

{
    "name": "Tech Supplies Inc",
    "email": "contact@techsupplies.com",
    "phone": "+1-555-0123"
}
```

## Database Configuration

The application uses H2 in-memory database with file persistence:

```properties
spring.datasource.url=jdbc:h2:file:./data/mydb
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

**Database Console Access:**
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:file:./data/mydb`
- Username: `sa`
- Password: (leave empty)

## Automated Features

### Low Stock Scheduler
- Runs every 5 minutes
- Monitors products below their low stock threshold
- Logs alerts to console
- Location: [`LowStockScheduler.java`](src/main/java/com/example/inventorymanagementsystem/component/LowStockScheduler.java)

## Project Structure

```
src/
├── main/
│   ├── java/com/example/inventorymanagementsystem/
│   │   ├── controller/          # REST controllers and web controllers
│   │   ├── service/             # Business logic layer
│   │   ├── repository/          # Data access layer
│   │   ├── model/               # Entity classes and DTOs
│   │   ├── security/            # Security configuration
│   │   ├── exception/           # Custom exceptions and handlers
│   │   └── component/           # Scheduled tasks and components
│   └── resources/
│       ├── templates/           # Thymeleaf HTML templates
│       ├── static/             # Static web assets
│       └── application.properties
└── test/                       # Test classes
```

## Security Features

- **Password Encryption**: BCrypt password encoding
- **Role-based Authorization**: Method-level security
- **CSRF Protection**: Configured for API usage
- **HTTP Security**: Secured endpoints with proper authentication

## Exception Handling

Global exception handling with custom exceptions:
- `ProductNotFoundException`
- `SupplierNotFoundException`
- `DuplicateProductException`
- `DuplicateSupplierException`

## Future Enhancements

- [ ] Add Unit Tests
- [ ] Add pagination for large datasets
- [ ] Implement advanced search and filtering
- [ ] Add email notifications for low stock
- [ ] Implement file upload for bulk operations

---

**Note**: This is a demonstration project showcasing Spring Boot, Spring Security, Spring Web, and modern web development practices in Java.
