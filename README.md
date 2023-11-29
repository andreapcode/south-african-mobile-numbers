
# **South African Mobile Numbers**

This project is a Spring Boot application that validates and inserts South African phone numbers from a CSV file into the H2 database using Java 17, Spring Boot 3, JPA, Maven, and Swagger3 with OpenAPI.

## Prerequisites

- Java 17 (or higher) must be installed on your system.
- Make sure you have Maven installed: [Maven Installation](https://maven.apache.org/install.html)

## Configuration

1. Clone this repository:

   ```bash
   git clone https://github.com/andreapcode/south-african-mobile-numbers.git 
   
2. Build the project:

   ```bash
    mvn clean install


## Running the Application
After building the project, you can run the Spring Boot application with the following command:

```bash
    mvn spring-boot:run
```

### H2 Database Configuration

The project uses H2 as an in-memory database.

You can access the H2 console during development. After starting the application, you can access http://localhost:8081/h2-console with the following credentials:

JDBC URL: jdbc:h2:mem:phonenumbersdb

User Name: admin

Password:  admin

### Using the API with Swagger

You can use the Swagger API documentation to validate and insert South African phone numbers. 

The Swagger UI is available at http://localhost:8081/swagger-ui.html


## API Endpoints

### Postman

A Postman collection(./postman_collection/south-african-numbers.postman_collection.json) is provided to access the application REST endpoints.

### POST
### saveAllPhoneNumbers:

Insert the CSV file (./src/main/resources/Interlogica_Test Pre-selection. South_African_Mobile_Numbers.csv) in the Body -> form-data section, putting the value "file" in the Key and the file in the Value field.


### GET
### findAllPhoneNumbers, findPhoneNumbersByStatus, findPhoneNumber:

Run these collections in their current state or by changing the parameters at your discretion.