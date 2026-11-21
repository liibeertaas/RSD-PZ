# Запити до API книгарні (заняття 1.1)

Базова адреса: `http://localhost:8080`

## Перелік книг (картки)
```bash
curl http://localhost:8080/api/books
```

## Одна книга з деталями
```bash
curl http://localhost:8080/api/books/1
```

## Неіснуюча книга → 404
```bash
curl -i http://localhost:8080/api/books/999
```
