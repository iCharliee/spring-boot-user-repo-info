# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.3/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.3/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.3/reference/htmlsingle/index.html#web)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.1.3/reference/htmlsingle/index.html#using.devtools)
# About
The app shows you a list all user's GitHub repositories after passing a username as a parameter to the endpoint

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/pl/java/technologies/downloads/#java17)
- [Git 2.x](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- [Gradle 8 (optional)](https://gradle.org/releases/)
- [Postman](https://www.postman.com/downloads/)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to clone the repository and execute the `main` method in the `com.springproject.userrepoinfo.SpringBootUserRepoInfoApplication` class from your IDE.

Alternatively you can use the [Gradle](https://docs.gradle.org/current/samples/sample_building_java_applications.html).

## How to test app?

After running server locally - open Postman and make an api request (username example: iCharliee)

```
[GET] http://localhost:8080/github/repos?userLogin=username
```

If you make a call with added header “Accept: application/xml", you will get 406 status code response:

```
{
    "status": 406,
    "Message": "XML format is not supported."
}
```

If you make a call with no username passed as parameter, you will get 404 status code response:

```
{
    "status": 404,
    "message": "User login is required."
}
```

If you make a call with non-existing username, you will get 404 status code response with other message:

```
{
    "status": 404,
    "message": "User login not found"
}
```
