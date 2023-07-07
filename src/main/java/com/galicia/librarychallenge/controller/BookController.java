package com.galicia.librarychallenge.controller;

import com.galicia.librarychallenge.dtos.ErrorResponse;
import com.galicia.librarychallenge.dtos.LoanRequest;
import com.galicia.librarychallenge.models.Book;
import com.galicia.librarychallenge.models.Loan;
import com.galicia.librarychallenge.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class BookController {

    private BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String title,
                                               @RequestParam(required = false) String author,
                                               @RequestParam(required = false) String category) {
        var output = service.searchBooks(title, author, category);
        return ResponseEntity.ok(output);

    }

    @GetMapping("/available-books")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        return ResponseEntity.ok(service.getAvailableBooks());
    }

    @PostMapping ("/loan")
    public ResponseEntity<?> bookLoan(@RequestBody LoanRequest request) {

        Loan loan = service.bookLoan(request.getCustomerId(), request.getBookId());
        if (Objects.isNull(loan)){
            return ResponseEntity.badRequest().body(new ErrorResponse("The book is not available"));
        }
        return ResponseEntity.ok(loan);

    }
}
