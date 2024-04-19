# User API

This project presents an example of a Spring Boot based REST API back-end for an application performing simple CRUD
operations on users.

## Available actions

- `GET /users` - Get all users
- `GET /users/{id}` - Get a single user
- `GET /users?name={name}` - Get users by name (exact match)
- `POST /users` - Create a new user
- `PUT /users/{id}` - Update a user
- `DELETE /users/{id}` - Delete a user

## User model

An example of the User object model handled by this REST API in JSON format:

```json
{
  "id": 1,
  "name": "Leanne Graham",
  "username": "Bret",
  "email": "Sincere@april.biz",
  "address": {
    "street": "Kulas Light",
    "suite": "Apt. 556",
    "city": "Gwenborough",
    "zipcode": "92998-3874",
    "geo": {
      "lat": "-37.3159",
      "lng": "81.1496"
    }
  },
  "phone": "1-770-736-8031 x56442",
  "website": "hildegard.org",
  "company": {
    "name": "Romaguera-Crona",
    "catchPhrase": "Multi-layered client-server neural-net",
    "bs": "harness real-time e-markets"
  }
}
```

## Persistence layer

MongoDB NoSQL database is used to persist the users. Program requires a connection to a locally running MongoDB instance
at port 27017.

## Building the application

The application can be built and packaged using the following Maven command:

```bash
mvn clean package
```

The resulting JAR file will be created in the 'target' folder and can then be used to start the application.

## Running the application

After building the application, it can be started using the following command:

```bash
java -jar user-api-0.0.1-SNAPSHOT.jar
```

The REST API can then be accessed at http://localhost:8080/user-api/users.

**NOTE:** The application requires a running MongoDB instance at localhost:27017.

### Running a local MongoDB instance with Docker

To run a local MongoDB instance with Docker, the following commands can be used:

```bash
docker pull mongodb/mongodb-community-server:latest
docker run --name mongodb -p 27017:27017 -d mongodb/mongodb-community-server:latest
```

## Testing

The REST API controller is unit tested using Spring Test MockMvc and [JUnit](https://junit.org/)
and [Mockito](https://mockito.org/) libraries.

## Dependencies

- Java 17+
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Boot Web](https://docs.spring.io/spring-boot/docs/current/reference/html/web.html)
- [Spring Boot Data MongoDB](https://spring.io/projects/spring-data-mongodb)
- [Spring Boot Test](https://docs.spring.io/spring-boot/reference/testing/index.html)
