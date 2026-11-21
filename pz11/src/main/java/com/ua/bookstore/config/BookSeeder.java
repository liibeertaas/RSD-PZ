package com.ua.bookstore.config;

import com.ua.bookstore.domain.Book;
import com.ua.bookstore.repository.BookRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BookSeeder implements ApplicationRunner {

    private final BookRepository repository;

    public BookSeeder(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) {
        repository.save(new Book(null, "Кобзар", "Тарас Шевченко",
                new BigDecimal("250.00"), 1840, "/covers/kobzar.jpg",
                "Збірка поетичних творів.", "Поезія"));
        repository.save(new Book(null, "Тіні забутих предків", "Михайло Коцюбинський",
                new BigDecimal("180.00"), 1911, "/covers/tini.jpg",
                "Повість про кохання Івана й Марічки на тлі гуцульських Карпат.", "Проза"));
        repository.save(new Book(null, "Лісова пісня", "Леся Українка",
                new BigDecimal("210.00"), 1911, "/covers/lisova-pisnia.jpg",
                "Драма-феєрія про Мавку та Лукаша.", "Драма"));
        repository.save(new Book(null, "Intermezzo", "Михайло Коцюбинський",
                new BigDecimal("120.00"), 1908, "/covers/intermezzo.jpg",
                "Імпресіоністична новела про втомлену людину та природу.", "Проза"));
        repository.save(new Book(null, "Зачарована Десна", "Олександр Довженко",
                new BigDecimal("195.50"), 1956, "/covers/desna.jpg",
                "Автобіографічна кіноповість про дитинство.", "Проза"));
    }
}
