# MinGuard BE

## Requirements

- Java 17
- Maven
- Docker

## Project run

```bash
$ sudo docker-compose up
$ mvn spring-boot:run
```

## API Documentation

- Swagger: http://localhost:8080/swagger-ui/index.html

## Best practices

1. Always remove unused imports [ctrl + alt + o in IntelliJ]
2. Always indent the code [ctrl + alt + l in IntelliJ]
3. Avoid creating data migrations while running the application. Sometimes it may cause issues
