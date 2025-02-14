Financial Transaction Tracking Service 
A reactive Spring Boot application for tracking and managing financial transactions using Spring WebFlux and R2DBC.
Overview
This service provides a reactive API for storing, retrieving, and filtering financial transactions. It's built with a focus on high throughput and non-blocking operations using Spring's reactive stack.
Features

Store and retrieve financial transaction records
Filter transactions by date range, user, service type, status, and reference
Pagination support for large result sets
Reactive endpoints with non-blocking I/O
PostgreSQL database with R2DBC for reactive data access

Technology Stack

Spring Boot 3.4
Spring WebFlux for reactive REST endpoints
Spring Data R2DBC for reactive database access
PostgreSQL as the database
R2DBC PostgreSQL driver
Project Lombok for reducing boilerplate code

Database Schema
sqlCopyCREATE TABLE financial_transactions (
    id SERIAL PRIMARY KEY,
    payment_id VARCHAR(50) NOT NULL,
    transaction_date DATE NOT NULL,
    user_id VARCHAR(50) NOT NULL,
    service VARCHAR(100) NOT NULL,
    status VARCHAR(50) NOT NULL,
    reference VARCHAR(100),
    amount DECIMAL(15,2) NOT NULL,
    currency VARCHAR(3) NOT NULL
);

CREATE INDEX idx_fin_trans_date ON financial_transactions(transaction_date);
CREATE INDEX idx_fin_trans_user ON financial_transactions(user_id);
CREATE INDEX idx_fin_trans_service ON financial_transactions(service);
CREATE INDEX idx_fin_trans_status ON financial_transactions(status);
CREATE INDEX idx_fin_trans_reference ON financial_transactions(reference);
Setup and Configuration
Prerequisites

JDK 23
PostgreSQL 17
Maven or Gradle

Configuration
Update the application.properties file with your database connection details:
propertiesCopyspring.r2dbc.url=r2dbc:postgresql://localhost:5432/yourdatabase
spring.r2dbc.username=yourusername
spring.r2dbc.password=yourpassword
spring.r2dbc.pool.enabled=true
spring.sql.init.mode=always


dateFrom: Filter transactions from this date (inclusive)
dateTo: Filter transactions until this date (inclusive)
userId: Filter by user ID
service: Filter by service type
status: Filter by transaction status
reference: Filter by reference code
page: Page number (0-based)
size: Number of records per page

Count Transactions with Filters
CopyGET /api/transactions/count?dateFrom=2024-01-01&dateTo=2024-12-31&userId=user123&service=Subscription&status=Completed&reference=SUB-2024-01
Parameters: Same as above, excluding pagination.
Key Implementation Details
Dynamic Query Building
The application uses a custom repository implementation to build dynamic queries based on the provided filter parameters. This approach:

Only includes conditions for non-null parameters
Avoids SQL grammar issues with NULL parameter handling
Provides efficient query execution
Maintains type safety through the Criteria API

R2DBC Entity Template
The R2dbcEntityTemplate is used for executing the dynamic queries, providing:

Reactive query execution
Proper parameter binding
Type conversion
Result mapping to entity objects

Database Initialization
Database schema and sample data are initialized using:

schema.sql for table and index creation
data.sql for sample transaction data
ConnectionFactoryInitializer for executing these scripts at application startup

The application uses non-blocking I/O for high throughput
Database indexes are created on commonly filtered columns
Pagination is implemented for large result sets
Connection pooling is enabled for efficient resource usage

Troubleshooting
Common Issues

Database Connection Problems

Verify PostgreSQL is running
Check connection parameters in application.properties
Ensure the database user has proper permissions

Query Performance

Enable query logging for debugging
Check execution plans for complex filters
Verify index usage for common queries


Contributing

Fork the repository
Create a feature branch (git checkout -b feature/your-feature)
Commit your changes (git commit -m 'Add some feature')
Push to the branch (git push origin feature/your-feature)
Open a Pull Request

License
This project is licensed under the MIT License - see the LICENSE file for details.

This README provides a comprehensive overview of the Financial Transaction Tracking Service, covering its architecture, setup, usage, and key implementation details. It serves as both documentation and a guide for developers working with the codebase. CopyRetryClaude does not have the ability to run the code it generates yet.Claude can make mistakes. Please double-check responses.
