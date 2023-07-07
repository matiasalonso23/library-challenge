package com.galicia.librarychallenge.repository;

import com.galicia.librarychallenge.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaBookRepository extends JpaRepository<Book, Long>, BookRepository {

    @Query("SELECT b FROM Book b WHERE b.id NOT IN (SELECT DISTINCT l.book.id FROM Loan l)")
    List<Book> getAvailableBooks();
}
