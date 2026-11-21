# Запити до API книгарні (заняття 1.2 — повний CRUD)

Базова адреса: `http://localhost:8080`

## 1. Перелік книг (картки)
```bash
curl http://localhost:8080/api/books
```

## 2. Фільтр за жанром
```bash
curl -G http://localhost:8080/api/books --data-urlencode "genre=Проза"
```

## 3. Одна книга з деталями (200 / 404)
```bash
curl http://localhost:8080/api/books/1
curl -i http://localhost:8080/api/books/999
```

## 4. Створити книгу (201)
```bash
curl -i -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Місто","author":"Валер'"'"'ян Підмогильний","price":230.00,"year":1928,"genre":"Проза","coverUrl":"/covers/misto.jpg","description":"Урбаністичний роман про Степана Радченка."}'
```

## 5. Оновити книгу (200 / 404)
```bash
curl -i -X PUT http://localhost:8080/api/books/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Кобзар (ювілейне видання)","author":"Тарас Шевченко","price":320.00,"year":1840,"genre":"Поезія","coverUrl":"/covers/kobzar.jpg","description":"Повне ювілейне видання."}'

curl -i -X PUT http://localhost:8080/api/books/999 \
  -H "Content-Type: application/json" \
  -d '{"title":"X","author":"Y","price":1,"year":2000,"genre":"Проза","coverUrl":"/x.jpg","description":"-"}'
```

## 6. Видалити книгу (204 / 404)
```bash
curl -i -X DELETE http://localhost:8080/api/books/2
curl -i -X DELETE http://localhost:8080/api/books/999
```
