package com.galicia.librarychallenge.service;

import com.galicia.librarychallenge.exceptions.NotFoundException;
import com.galicia.librarychallenge.models.Book;
import com.galicia.librarychallenge.models.Customer;
import com.galicia.librarychallenge.models.Loan;
import com.galicia.librarychallenge.repository.JpaBookRepository;
import com.galicia.librarychallenge.repository.JpaCustomerRepository;
import com.galicia.librarychallenge.repository.JpaLoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private JpaBookRepository jpaBookRepository;
    private JpaLoanRepository jpaLoanRepository;
    private JpaCustomerRepository jpaCustomerRepository;

    public BookServiceImpl(JpaBookRepository jpaBookRepository, JpaLoanRepository jpaLoanRepository, JpaCustomerRepository jpaCustomerRepository) {
        this.jpaBookRepository = jpaBookRepository;
        this.jpaLoanRepository = jpaLoanRepository;
        this.jpaCustomerRepository = jpaCustomerRepository;
    }

    @Override
    public List<Book> getAvailableBooks() {
        return jpaBookRepository.getAvailableBooks();
    }

    @Override
    public List<Book> searchBooks(String title, String author, String category) {
        return jpaBookRepository.searchBooks(title, author, category);
    }

    @Override
    public Loan bookLoan(Long customerId, Long bookId) {
        Book book = jpaBookRepository.findById(bookId).orElseThrow(() ->
                new NotFoundException("Book not found with id: " + bookId));
        Customer customer = jpaCustomerRepository.findById(customerId).orElseThrow(() ->
                new NotFoundException("Customer not found with id: " + customerId));
        List<Loan> loans = jpaLoanRepository.findAll();
        if (loans.stream().anyMatch(l -> l.getBook().getId().equals(bookId))){
            return null;
        }
        return jpaLoanRepository.save(new Loan(book, customer, LocalDateTime.now()));
    }
}
