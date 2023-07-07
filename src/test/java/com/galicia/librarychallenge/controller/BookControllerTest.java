package com.galicia.librarychallenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galicia.librarychallenge.dtos.LoanRequest;
import com.galicia.librarychallenge.models.*;
import com.galicia.librarychallenge.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    BookService bookService;

    @Test
    void getBooks() throws Exception {
        String authorParam = "Stephen King";
        String categoryParam = "Horror";
        String titleParam = null;


        Author author = new Author("Stephen King");
        Category category = new Category("Horror");
        String bookResponseTitle = "book title";
        Book book = new Book(bookResponseTitle, author, category);

        List<Book> response = new ArrayList<>();
        response.add(book);

        when(bookService.searchBooks(titleParam, authorParam, categoryParam)).thenReturn(response);

        mvc.perform(get("/books?author=Stephen King&category=Horror")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(bookResponseTitle))
                .andExpect(jsonPath("$[0].author.name").value("Stephen King"));
    }

    @Test
    void getAvailableBooks() throws Exception {

        Author author = new Author("Stephen King");
        Category category = new Category("Horror");
        String bookResponseTitle = "book 1";
        String bookResponseTitle2 = "book 2";
        Book book = new Book(bookResponseTitle, author, category);
        Book book2 = new Book(bookResponseTitle2, author, category);

        List<Book> response = new ArrayList<>();
        response.add(book);
        response.add(book2);

        when(bookService.getAvailableBooks()).thenReturn(response);

        mvc.perform(get("/available-books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].title").value(bookResponseTitle2))
                .andExpect(jsonPath("$", hasSize(2)));

    }

    @Test
    void bookLoan_ok() throws Exception {
        Author author = new Author("Stephen King");
        Category category = new Category("Horror");
        String bookTitle = "book title";
        Book book = new Book(bookTitle, author, category);
        book.setId(1L);
        Customer customer = new Customer("Bob");
        customer.setId(1L);
        Loan mockedLoan = new Loan(book, customer, LocalDateTime.now());

        when(bookService.bookLoan(customer.getId(), book.getId())).thenReturn(mockedLoan);

        LoanRequest loanRequest = new LoanRequest(1L, 1L);
        var loanRequestString = new ObjectMapper().writeValueAsString(loanRequest);

        mvc.perform(post("/loan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loanRequestString))
                .andExpect(status().isOk());
    }

    @Test
    void bookLoan_badRequest() throws Exception {
        when(bookService.bookLoan(1L, 1L)).thenReturn(null);

        LoanRequest loanRequest = new LoanRequest(1L, 1L);
        var loanRequestString = new ObjectMapper().writeValueAsString(loanRequest);

        mvc.perform(post("/loan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loanRequestString))
                .andExpect(status().isBadRequest());

    }
}