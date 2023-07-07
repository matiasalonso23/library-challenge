package com.galicia.librarychallenge.repository;

import com.galicia.librarychallenge.models.Book;

import java.util.List;

public interface BookRepository {

    List<Book> searchBooks(String title, String author, String category);

}
