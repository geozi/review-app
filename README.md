# review-app
![Demo App](https://img.shields.io/badge/demo_app-blue)

### About the project
The uploaded code contains the backend implementation of a review app. A User logs in and, after a successful authentication, s/he can post a review that is persisted on a MongoDB database. 

### Backend Framework

A development goal was to create the backend using Java, thus the Spring framework was chosen. 

### Dependencies

Using the Spring Initializr (https://start.spring.io/), the following dependencies were installed:

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
### Prerequisites

After creating the project with Spring Initializr, the following must exist in order for the build to take place:
* Java 17 or higher
* IDE (Intellij or Eclipse)
* MongoDB (locally installed or on MongoDB Atlas)

### Testing tools

To test the REST APIs, a third-party tool can be used, e.g Postman.


<p align="center">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=java,gradle,spring,mongo,postman&theme=light" />
  </a>
</p>
