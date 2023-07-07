package com.galicia.librarychallenge.repository;

import com.galicia.librarychallenge.models.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepositoryImpl implements BookRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> searchBooks(String title, String author, String category) {
        String query = "SELECT b FROM Book b JOIN b.category c JOIN b.author a WHERE 1 = 1";
        Map<String, String> params = new HashMap<>();

        if (StringUtils.hasText(title)) {
            query += " AND b.title = :title";
            params.put("title", title);
        }

        if (StringUtils.hasText(category)) {
            query += " AND c.name = :category";
            params.put("category", category);
        }

        if (StringUtils.hasText(author)) {
            query += " AND a.name = :author";
            params.put("author", author);
        }

        TypedQuery<Book> typedQuery = entityManager.createQuery(query, Book.class);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            typedQuery.setParameter(entry.getKey(), entry.getValue());
        }

        return typedQuery.getResultList();
    }
}
