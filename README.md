# Student2: Spring Boot CRUD with JWT

## Run
1. Open `src/main/resources/application.properties` and set your MySQL password.
2. Run `Student2Application` (the database `studentdb` is created automatically).

## Test in Postman
1. `POST http://localhost:8080/login` with JSON body `{"username":"admin","password":"1234"}`. Copy the token you get back.
2. `GET http://localhost:8080/students` with no token returns 403.
3. `GET http://localhost:8080/students` with header `Authorization: Bearer <token>` returns 200.
4. `POST /students` with the token and body `{"name":"Ravi","age":21,"email":"r@x.com","course":"Java"}` creates a student.

## JWT files (the only new parts on top of plain CRUD)
| File | Job |
|---|---|
| `security/JwtUtil` | makes and reads tokens |
| `controller/AuthController` | `/login` gives the token |
| `security/JwtFilter` | checks the token on every request |
| `config/SecurityConfig` | rules: which URLs are public or locked |

## Where the boilerplate comes from
- JJWT dependencies and `Jwts.builder()` / `Jwts.parser()` chains: https://github.com/jwtk/jjwt
- `SecurityFilterChain` shape and `OncePerRequestFilter` shell: https://docs.spring.io/spring-security/reference
