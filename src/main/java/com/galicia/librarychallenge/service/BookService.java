package com.galicia.librarychallenge.service;

import com.galicia.librarychallenge.models.Book;
import com.galicia.librarychallenge.models.Loan;

import java.util.List;

public interface BookService {

    public List<Book> getAvailableBooks();
    public List<Book> searchBooks(String title, String author, String category);
    public Loan bookLoan(Long customerId, Long bookId);

}
