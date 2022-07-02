# MoviesSystemCC
Movies System Repository Back-end

There are two APIs in this project. It was developed in a Windows SO, using Visual Studio Code 2022 as an IDE.

To use it, run first the Authentication API, then run the MoviesSystem one.

What is missing from this project requisites:
 - Only ADVANCED users can refernce other comments in theirs;
 - Limit of attempts when the user has an invalid token or login and keep this info in a cache;

Info:
- Maven Project
- Language: Java 17 (jdk-17.0.3.7)
- Spring Boot: 2.7.1;
- Packaging: Jar;

After lauching both applications, you can use Swagger docummentation to test the endpoints:
- http://localhost:8080/swagger-ui.html#/ - For MoviesSystem
- http://localhost:8081/swagger-ui.html#/ - For Authentication

You can also use Postman Workspace to get uses of each endpoint, available in the _postman_requests_ directory

Structure:
- MoviesSystem/src/main/java/com/CC/MoviesSystem
- MoviesSystemApplication.java: main application
- AuthenticationApplication.java: security application
- SwaggerConfig.java: swagger config class
- controller: controllers to process requests (endpoints impl)
- dto: input and output data classes for the requests (I decided to add verbose DTOs as outputs for testing metters)
- entity: storage data classes (entities impl)
- enumeration: profile and reaction enum
- exception: all exceptions used in this project
- repository: interface with the database (in memory h2)
- service: business logic layer (between the controllers and repositories)
