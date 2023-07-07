package com.galicia.librarychallenge.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoanRequest {

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("book_id")
    private Long bookId;

    public LoanRequest(Long customerId, Long bookId) {
        this.customerId = customerId;
        this.bookId = bookId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
