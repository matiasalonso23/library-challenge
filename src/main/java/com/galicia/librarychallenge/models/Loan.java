package com.galicia.librarychallenge.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "fk_loan_book"))
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_loan_customer"))
    private Customer customer;

    private LocalDateTime loanDate;

    public Loan() {
    }

    public Loan(Book book, Customer customer, LocalDateTime loanDate) {
        this.book = book;
        this.customer = customer;
        this.loanDate = loanDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDateTime loanDate) {
        this.loanDate = loanDate;
    }

    // Optional: toString() method for debugging and logging purposes

}
