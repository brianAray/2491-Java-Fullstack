# Project 1: Spring Boot Banking App

# Description

Using Spring Boot, create a REST Web App that simulates banking transactions. You are assigned to work in pairs during this project.

## Requirements

- Build the application using Spring Boot
- Ensure a 3 tier structure for the application (Controller, Service, and Repository)
- All interaction by the user should be done through the controller via HTTP Requests, following REST constraints
- Customers of the bank should be able to register with a username and password, and apply to open an account
- Customers should be able to apply for joint accounts (share accounts with other users)
- Once the account is open, customers should be able to withdraw, deposit, and transfer funds between accounts
    - All basic validation should be done, such as preventing users from trying to input negative amounts, overdrawing from accounts, etc.
- Employees of the bank should be able to view all of their customers' information. This includes:
    - Account information
    - Account balances
    - Personal information
- Employees should be able to approve/deny open applications for accounts
- Bank admins should be able to view and edit all accounts. This includes:
    - Approving/denying accounts
    - Withdrawing, depositing, transferring from ALL accounts
    - Cancelling accounts
- Reasonable test coverage for the service layer is expected using JUnit
    - TDD is encouraged
    - Aim for 80% coverage for the service layer
- API testing should be done through Postman
- Create a SQL script that will create all tables necessary for the application to store bank users and account information
    - Inserting sample data is also encouraged for manual testing/presentation purposes
    - Stretch Goal: database includes at least 1 stored procedure that you invoke from the application
- Have your bank application connect to the PostgreSQL AWS RDS using Spring JPA
- The repository layer should use entities and DTOs to prevent circular dependencies with Spring JPA

## Presentation

- You are given 10 minutes to present the application and 5 minutes for any questions, altogether, 15 minutes
- You should not need to show any code during the presentation, it should just be the application running and you narrating over the features you have implemented
