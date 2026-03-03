## Reward-program
This is a Spring Boot REST API that calculates customer reward points based on their transactions.
The application provides endpoints to:
- View all transactions
- View reward points for a specific customer
- View reward points for all customers
##Features 
Reward points calculation:
    - 2 points for every dollar spent over $100
    - 1 point for every dollar spent between $50 and $100
    - Example: $120 → (120 − 100)×2 + (100 − 50)×1 = 90 points
- RESTful API design
- Input validation with 400 Bad Request for invalid inputs
- Global exception handling with standardized error responses
- Pagination support for transaction listing

## Tech stack
Java 17+
Spring Boot
Spring Web
Spring Data JPA
H2 In-Memory Database

## How to run the application
1. Clone the Repository
git clone https://github.com/your-username/rewards-api.git
cd rewards-api
2. Build the Project
If you're using Maven:
./mvnw clean install
Or:
mvn clean install
3. Run the Application
./mvnw spring-boot:run
Or run the main class from your IDE.
The application will start on:
http://localhost:8080
4. Access H2 Database Console
http://localhost:8080/h2-console
Use these settings(just for practice):
Property	Value
JDBC URL	jdbc:h2:mem
User	sa
Password	(leave empty)

## Database Setup
Tables are initialized using `src/main/resources/schema.sql`  
The following tables exist:
- `customer` (id, name)
- `transactions` (id, customer_id, amount, transaction_date)  

## API Endpoints
Transactions
- GET `/rewards/transactions?page=0&size=10` - Get all transactions (paginated)

Customer Rewards
- GET `/rewards/{customerId}` - Get rewards for a specific customer
- GET `/rewards` - Get rewards for all customers

Notes:
- Non-existent resources return `404 Not Found`
- All responses are wrapped in a standardized `ApiResponse` containing:
    - `content` - response data
    - `businessCode` - HTTP code or custom code
    - `timestamp` - response time  

## Example Reward Calculation
A transaction of $120:
    - Amount over $100 → 20 × 2 = 40 points
    - Amount between $50 and $100 → 50 × 1 = 50 points
    - Total points = 90

