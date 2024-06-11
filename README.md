# review-app
![Demo App](https://img.shields.io/badge/demo_app-blue)

### About the project
This repository contains the backend implementation of my final project for the [Coding Factory](https://codingfactory.aueb.gr/) training program. The project is a fullstack implementation of a generic review app. A user can register, log in, and post a review that is persisted on MongoDB Atlas. 

### Business logic

There are 2 fundamental business flows supported: user registration and CRUD operations for reviews.

![review_app_backend](https://github.com/geozi/review-app/assets/153010644/2eaa6379-ea98-4065-935c-bb3ce60dfd2c)

### Prerequisites

* Java 17 or higher,
* A Java IDE (Intellij or Eclipse),
* MongoDB (locally installed or on MongoDB Atlas)

### Dependencies

Using the Spring Initializr (https://start.spring.io/), the following dependencies should be installed:

```
	implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	compileOnly 'org.projectlombok:lombok'
	annotationProcessor 'org.projectlombok:lombok'
	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.security:spring-security-test'
	implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0'
	implementation 'io.jsonwebtoken:jjwt-api:0.12.5'
	runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.12.5'
	runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.12.5'
```
### Base URL

The default value of the base URL for API endpoints is:

```
http://localhost:8080
```

### API documentation

Swagger is the framework of choice for documenting API endpoints. The default url for accessing the Swagger UI is:

```
http://localhost:8080/swagger-ui/index.html
```


<p align="center">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=java,gradle,spring,mongo,postman&theme=light"/>
	 
  </a>
</p>
