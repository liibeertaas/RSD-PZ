package com.ua.bookstore.web.dto;

import java.math.BigDecimal;

/** Вхідний DTO для створення/оновлення книги — без id (його призначає сервер). */
public record BookRequest(
        String title, String author, BigDecimal price,
        Integer year, String genre, String coverUrl, String description
) {
}
