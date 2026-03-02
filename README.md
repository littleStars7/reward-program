# reward-program
This is a Spring Boot REST API that calculates customer reward points based on their transactions.
The application provides endpoints to:
View all transactions
View reward points for a specific customer
View reward points for all customers
# tech stack
Java 17+
Spring Boot
Spring Web
Spring Data JPA
H2 In-Memory Database
# how to run the application
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
Use these settings:
Property	Value
JDBC URL	jdbc:h2:mem
User	sa
Password	(leave empty)
