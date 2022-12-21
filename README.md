## Introduction

This is the authentication Service for Smoothie Application

## The Smoothie Authentication Service

The service has a few requirements that is been implemented

### 1. Register Customers 
Customers can be registered with their basic details. There is list of email ids which are whitelisted in order for them to be admin. Register with them to become a business owner or else dummy email ids can be used as end-users.

###  2. Login and Authenticate Customers
Customer should enter valid credentials in order to be authenticated and get the JWT Access Token. Use correct credentials in order to authenticate the customer. The JWT Token returned will consist of the claims containing emailId and role of the customer.

## Technical Details

The service is written in Java and uses Spring Boot as an underlying framework. 
I used Postgres to manage database migrations and [JPA].

### Code Structure

All API endpoints are located in `com.nox.controller` package. Open API.yml to be added soon.

## Running the service with an IDE (i.e. IntelliJ IDEA, STS Eclipse)

Clone the service to your computer using the command below:

```sh
git clone https://github.com/Nox69/smoothie-authentication-service.git
cd smoothie-authentication-service
```

Run the Postgres database using the command below: (Docker must be installed)

```sh
docker-compose up db
```

Import the service in your favourite IDE, i.e. IntelliJ IDEA and execute the main class `com.nox.AuthenticationServiceApplication` to start the service.

## Running the service without an IDE

Clone the service to your computer using the command below:

```sh
git clone https://github.com/Nox69/smoothie-authentication-service.git
cd smoothie-authentication-service
```

Start/update the service using the command below:

```sh
docker-compose up -d --build
mvn clean -DskipTests=true install
docker build -t smoothie-authentication-service .
docker run -p 8084:8084 smoothie-authentication-service
```

Stop the service using the command below:

```sh
docker-compose down
```

Watch the application logs using the command below

```sh
docker logs authentication-service -f
```

## Interacting with the API

### Authentication endpoints 

Run the following cURL command to create a new customer in the service.

```sh
curl -X POST \
    -H "Content-Type: application/json" \
    -d '{
    "emailId": "leelaBindu@gmail.com",
    "password": "leela",
    "name": "Leela Bindu"
    }' http://localhost:8084/v1/api/auth/register -v
```

Run the following cURL command to authenticate and retrieve the access Token.

```sh
curl -X POST \
    -H "Content-Type: application/json" \
    -d '{
    "emailId": "admin@gmail.com",
    "password" : "bharat"
    }' http://localhost:8084/v1/api/auth/login -v
```