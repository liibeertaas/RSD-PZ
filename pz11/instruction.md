# Інструкція — ПЗ 1.1 (книгарня, два GET-ендпоінти)

Завдання: `lessons/gz11-first-endpoints/gz11_student_task.md`.

## Що зроблено
Spring Boot 3.4.13 / Java 21 / Maven, пакет `com.ua.bookstore`, сутність `Book`.

```
src/main/java/com/ua/bookstore/
├── BookstoreApplication.java        — точка входу
├── domain/Book.java                 — доменна модель (без анотацій Spring)
├── repository/
│   ├── BookRepository.java          — інтерфейс сховища
│   └── InMemoryBookRepository.java  — реалізація в пам'яті (ConcurrentHashMap + AtomicLong)
├── config/BookSeeder.java           — 5 стартових книг (ApplicationRunner)
├── service/
│   ├── BookService.java             — інтерфейс сервісу
│   ├── BookServiceImpl.java         — реалізація (findAll, findById)
│   └── mapper/BookMapper.java       — Book → BookCardDto / BookDetailDto
├── exception/BookNotFoundException.java — @ResponseStatus(404)
└── web/
    ├── BookController.java          — /api/books
    └── dto/BookCardDto.java, BookDetailDto.java (record)
```

Ендпоінти:
| Метод | URL | Відповідь |
|---|---|---|
| GET | `/api/books` | масив карток `BookCardDto` |
| GET | `/api/books/{id}` | `BookDetailDto` або `404` |

## Як запустити
- IntelliJ IDEA: відкрити папку `pz11` як Maven-проєкт, запустити `BookstoreApplication`.
- Або з терміналу (потрібен JDK 21+):
  ```bash
  ./mvnw spring-boot:run
  ```
У консолі з'явиться `Started BookstoreApplication`.

## Як перевірити
Див. `requests.md`:
```bash
curl http://localhost:8080/api/books
curl http://localhost:8080/api/books/1
curl -i http://localhost:8080/api/books/999   # 404
```

## Що сказати на захисті
- Шари domain / repository / service / web, на межах — інтерфейси (`BookRepository`, `BookService`) → DIP із SOLID.
- Залежності впроваджуються через конструктор.
- Назовні віддаються DTO, а не `Book`: картка — мінімум полів, деталі — повний набір.
- Дані поки додаються лише в коді (`BookSeeder`), бо `POST` ще немає.
- 404 — через виняток з `@ResponseStatus(HttpStatus.NOT_FOUND)`.

## Що здати
Власний git-репозиторій з цим проєктом (за умовою — окремий від репозиторію викладача), `requests.md`, 5 стартових книг.
