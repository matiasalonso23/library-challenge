package com.galicia.librarychallenge.service;

import com.galicia.librarychallenge.exceptions.NotFoundException;
import com.galicia.librarychallenge.models.*;
import com.galicia.librarychallenge.repository.JpaBookRepository;
import com.galicia.librarychallenge.repository.JpaCustomerRepository;
import com.galicia.librarychallenge.repository.JpaLoanRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    JpaBookRepository bookRepository;

    @Mock
    JpaLoanRepository loanRepository;

    @Mock
    JpaCustomerRepository customerRepository;

    @InjectMocks
    BookServiceImpl bookService;

    @Test
    void getAvailableBooks() {
        Author author = new Author("Stephen King");
        Category category = new Category("Horror");
        String bookResponseTitle = "book 1";
        String bookResponseTitle2 = "book 2";
        Book book = new Book(bookResponseTitle, author, category);
        Book book2 = new Book(bookResponseTitle2, author, category);

        List<Book> repositoryResponse = new ArrayList<>();
        repositoryResponse.add(book);
        repositoryResponse.add(book2);

        when(bookRepository.getAvailableBooks()).thenReturn(repositoryResponse);
        var serviceResponse = bookService.getAvailableBooks();

        assertEquals(serviceResponse.size(), repositoryResponse.size());
        assertEquals(serviceResponse.get(0).getTitle(), repositoryResponse.get(0).getTitle());
        verify(bookRepository, atLeastOnce()).getAvailableBooks();
    }

    @Test
    void searchBooks() {
        String authorParam = "Stephen King";
        String categoryParam = "Horror";
        String titleParam = "bookTitle";

        Author author = new Author("Stephen King");
        Category category = new Category("Horror");
        String bookResponseTitle = "bookTitle";
        Book book = new Book(bookResponseTitle, author, category);

        List<Book> repositoryResponse = new ArrayList<>();
        repositoryResponse.add(book);

        when(bookRepository.searchBooks(titleParam, authorParam, categoryParam)).thenReturn(repositoryResponse);
        var serviceResponse = bookService.searchBooks(titleParam, authorParam, categoryParam);

        assertEquals(serviceResponse.size(), repositoryResponse.size());
        assertEquals(serviceResponse.get(0).getTitle(), repositoryResponse.get(0).getTitle());
        verify(bookRepository, atLeastOnce()).searchBooks(titleParam, authorParam, categoryParam);
    }

    @Test
    void bookLoan_bookAvailable_saveLoan() {
        Long customerId = 1L;
        Long bookId = 1L;

        //book to loan
        Book bookToLoan = new Book();
        bookToLoan.setId(1L);
        bookToLoan.setTitle("title");

        Customer customer = new Customer("customer");
        customer.setId(1L);

        Loan newLoan = new Loan(bookToLoan, customer, LocalDateTime.now());

        // other existing loan
        Book bookLoaned = new Book();
        bookLoaned.setId(2L);
        bookLoaned.setTitle("title");

        Customer customer2 = new Customer("customer2");
        customer.setId(2L);

        Loan existingLoan = new Loan(bookLoaned, customer2, LocalDateTime.now());

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookToLoan));
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(loanRepository.findAll()).thenReturn(List.of(existingLoan));
        when(loanRepository.save(any())).thenReturn(newLoan);

        var actualLoan = bookService.bookLoan(customerId, bookId);

        assertEquals(actualLoan.getBook().getId(), newLoan.getBook().getId());
        assertEquals(actualLoan.getCustomer().getId(), newLoan.getCustomer().getId());

    }

    @Test
    void bookLoan_bookUnavailable_returnNull() {
        Long customerId = 1L;
        Long bookId = 1L;

        //book to loan
        Book bookToLoan = new Book();
        bookToLoan.setId(1L);
        bookToLoan.setTitle("title");

        Customer customer = new Customer("customer");
        customer.setId(1L);

        Loan newLoan = new Loan(bookToLoan, customer, LocalDateTime.now());

        // existing loan, same book
        Customer customer2 = new Customer("customer2");
        customer.setId(2L);

        Loan existingLoan = new Loan(bookToLoan, customer2, LocalDateTime.now());

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookToLoan));
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(loanRepository.findAll()).thenReturn(List.of(existingLoan));

        var actualLoan = bookService.bookLoan(customerId, bookId);

        assertNull(actualLoan);
    }

    @Test
    void bookLoan_bookNotFound_throwException() {
        Long customerId = 1L;
        Long bookId = 1L;

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> bookService.bookLoan(customerId, bookId));
    }


}