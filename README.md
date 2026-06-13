# SHROOMS SCAFFOLD SOLUTIONS

Shrooms Scaffold Solutions is a Spring Boot web application for scaffold rental, purchase, and custom scaffold requests.

## Features

- User registration and login
- User profile and edit profile
- Scaffold catalog
- Scaffold rental and purchase
- Custom scaffold requests
- User order history
- Admin dashboard
- Admin order management
- Admin custom request management
- Admin scaffold create/edit/delete
- Email notification when order status changes
- Scheduled pending orders report
- Cached scaffold catalog

## Roles

### Guest
- Can view the home page and public pages
- Can register and login

### User
- Can rent and purchase scaffolds
- Can submit custom scaffold requests
- Can view personal orders
- Can edit profile

### Admin
- Can view all rent and purchase orders
- Can update order statuses
- Can review custom scaffold requests
- Can set estimated prices
- Can create, edit and delete scaffolds
- Cannot use the customer order flow

## Technologies

- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- MySQL
- Hibernate Validator
- Spring Mail
- Spring Scheduling
- Spring Cache

## Covered Topics

- HTTP Sessions
- Servlet Interceptors
- Spring Events
- Scheduling Tasks
- Caching
- Validation
- CRUD operations

## Environment Variables

- `DB_PASSWORD`
- `MAIL_USERNAME`
- `MAIL_PASSWORD`

## Admin Account

Username: `admin`  
Password: `shrooms123`