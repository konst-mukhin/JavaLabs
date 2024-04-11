# "Country-capital"
## Description
"Country-capital" project is a web/REST service whith provides GET, POST, PUT, DELETE requests. All returns in JSON format.
## Technologies
- Java Spring Boot
- PostgreSQL
## Installation
### You need to install the following
- VSCode
- JDK
- PostgreSQL
- Add dependencies and exetensions
### Main class
```Java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MySpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }
}
```
### Use http://localhost:8080!