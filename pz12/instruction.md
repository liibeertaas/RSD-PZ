# Інструкція — ПЗ 1.2 (книгарня, повний CRUD)

Завдання: `lessons/gz12-crud/gz12_student_task.md`.
Основа — проєкт `pz11`, доповнений до повного CRUD + фільтр за жанром.

## Що додано порівняно з pz11
- `repository/BookRepository` + `InMemoryBookRepository`: `findByGenre(String)`, `deleteById(Long)`.
- `web/dto/BookRequest.java` — вхідний DTO **без `id`** (id призначає сервер).
- `service/mapper/BookMapper.toDomain(BookRequest)`.
- `BookService` / `BookServiceImpl`: `findAll(genre)`, `create`, `update`, `delete`.
- `BookController`: `@RequestParam genre`, `POST`, `PUT`, `DELETE`.

Ендпоінти:
| Метод | URL | Успіх | Помилка |
|---|---|---|---|
| GET | `/api/books` | 200, картки | — |
| GET | `/api/books?genre=Проза` | 200, відфільтровані (без урахування регістру) | — |
| GET | `/api/books/{id}` | 200 | 404 |
| POST | `/api/books` | **201** + створена книга | — |
| PUT | `/api/books/{id}` | 200 + оновлена книга | 404 |
| DELETE | `/api/books/{id}` | **204** (без тіла) | 404 |

## Як запустити
- IntelliJ IDEA: відкрити папку `pz12` як Maven-проєкт, запустити `BookstoreApplication`.
- Або: `./mvnw spring-boot:run` (JDK 21+).

## Як перевірити
Усі шість операцій з прикладами `curl` — у `requests.md`. Коротко:
```bash
curl -G http://localhost:8080/api/books --data-urlencode "genre=Проза"
curl -i -X POST http://localhost:8080/api/books -H "Content-Type: application/json" \
  -d '{"title":"Нова","author":"Автор","price":199.00,"year":2024,"genre":"Проза","coverUrl":"/covers/x.jpg","description":"Опис"}'
curl -i -X PUT http://localhost:8080/api/books/1 -H "Content-Type: application/json" \
  -d '{"title":"Оновлена","author":"Автор","price":250.00,"year":2024,"genre":"Проза","coverUrl":"/covers/x.jpg","description":"Опис"}'
curl -i -X DELETE http://localhost:8080/api/books/2
```
> Дані в пам'яті — після перезапуску повертаються стартові 5 книг.

## Що сказати на захисті
- Тіло запиту читається через `@RequestBody`, фільтр — через `@RequestParam(required = false)`.
- Окремий вхідний DTO без `id`: клієнт не може підмінити ідентифікатор; у `create` id ставиться `null`, у `update` — береться з URL.
- `update` спочатку перевіряє існування → 404; `delete` повертає 404, якщо `deleteById` дав `false`.
- Коди: `201 Created` (`@ResponseStatus`), `204 No Content` (`ResponseEntity.noContent()`).
- Шари та інтерфейси — як у 1.1.
