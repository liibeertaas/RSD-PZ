package com.ua.bookstore.repository;

import com.ua.bookstore.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryBookRepository implements BookRepository {

    private final Map<Long, Book> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>(storage.values());
        books.sort(Comparator.comparing(Book::getId));
        return books;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Book> findByGenre(String genre) {
        return findAll().stream()
                .filter(b -> genre.equalsIgnoreCase(b.getGenre()))
                .toList();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(sequence.incrementAndGet());
        }
        storage.put(book.getId(), book);
        return book;
    }

    @Override
    public boolean deleteById(Long id) {
        return storage.remove(id) != null;
    }
}
